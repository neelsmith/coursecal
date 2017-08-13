package edu.holycross.shot.coursecal

import scala.io.Source
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap


/** A sequence of topics for a course.
*
* @param entries Sequence of [[TopicEntry]]s for the semester.
*/
case class Topics (entries : Vector[TopicEntry] ) {


  /** Number of entries. */
  def size: Int = entries.size

  /** Cluster [[CourseDay]] entries into groups
  * organized by [[TopicEntry]]s
  */
  def segments = {
    addSegment(entries,Vector(Topics(Vector.empty[TopicEntry])) )
  }

  def addSegment(src: Vector[TopicEntry], target: Vector[Topics]) : Vector[Topics] = {
    if (src.isEmpty) {
      target.filter(_.entries.nonEmpty)

    } else {
      val nextSegment: Vector[TopicEntry] = Topics.nextSegment(src, Vector.empty[TopicEntry] )
      if (target.size == 0) {
        addSegment(src.drop(nextSegment.size), Vector(Topics(nextSegment)))
      } else {
        val composite =   target ++  Vector(Topics(nextSegment))
        addSegment(src.drop(nextSegment.size), composite)
      }
    }
  }





  /** Aggregate sequence of [[TopicEntry]]s in weekly
  * sequences.
  *
  * @param classesPerWeek Number of class meetings per Week.
  */
  def weekly(classesPerWeek: Int)  = {  ///    : Vector[Vector[TopicEntry]] = {
      val clustered = Vector.empty
      println("WEEKLY:")
      addWeek(days, clustered, classesPerWeek)
  }


  def addWeek(source: Vector[TopicEntry], clustered: Vector[Vector[TopicEntry]], classesPerWeek: Int) = {
    if (source.size > classesPerWeek) {
      println("TAKE: " + source.take(classesPerWeek))

    } else {
      println("done")
    }
  }


  /** Compute number of calendar weeks required for entries
  * for a schedule with a given number of meetings per week.
  *
  * @param classesPerWeek Number of class meeting per week.
  */
  def weeks(classesPerWeek: Int) : Int = {
      val dayCount = days.size
      val wholeWeeks = dayCount /  classesPerWeek
      val rem = dayCount % classesPerWeek
      if ( rem > 0) {
        wholeWeeks + 1
      } else {
        wholeWeeks
      }
  }

  /** Extract [[CourseDay]] entries from the list.
  */
  def days: Vector[TopicEntry] = entries.filter(Topics.isDay(_))
}



/** Factory object for creating a [[Topics]] from a
* delimited-text file.
*/
object Topics {
/*
  def fromText(s:String): Topics = {
    val hdr = "^#.+".r
    val entries = s.split("\n").map( l =>
      hdr.findFirstIn(l) match {
        case None =>  CourseDay(l)
        case h: Some[String] => Some(SectionTopic(l))
      }
    )
    Topics(entries.map(_.get))
  }*/

  /** Create a [[Topics]] from a delimited-text file.
  *
  * @param syllabusFileName Name of file with sequence of course topics.
  */
  def apply(syllabusFileName: String): Topics = {
    val hdr = "^#.+".r
    val lns = scala.io.Source.fromFile(syllabusFileName).getLines().toVector.filter(_.nonEmpty)
    val entries = lns.map( l =>
      hdr.findFirstIn(l) match {
        case None =>  CourseDay(l)
        case h: Some[String] => Some(SectionTopic(l))
      }
    )
    Topics(entries.map(_.get))
  }

  /** True if entry is a [[CourseDay]].
  */
  def isDay(entry: TopicEntry) : Boolean  = entry match {
      case day: CourseDay => true
      case _ => false
  }

  /**  Gather all [[CourseDay]] entires from a topic list up
  * until the next [[TopicEntry]], collecting a heading value with
  * the list if it begins with a heading.
  */
  def nextSegment(src: Vector[TopicEntry], target: Vector[TopicEntry]): Vector[TopicEntry] = {
    if (src.isEmpty) {
      target
    } else {
      val nextVal = src(0)
      nextVal match {
        case day : CourseDay => {
          nextSegment(src.drop(1), Vector(day) ++ target )
        }
        case topic : SectionTopic => {
          // accept section heading at beginning of segment
          if (target.size == 0) {
            nextSegment(src.drop(1), Vector(topic))
          } else {
            target
          }
        }
      }
    }
  }
}
