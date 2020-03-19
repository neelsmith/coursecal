package edu.holycross.shot.coursecal

import scala.io.Source



import wvlet.log._
import wvlet.log.LogFormatter.SourceCodeLogFormatter


/** A sequence of topics for a course.
*
* @param entries Sequence of [[TopicEntry]]s for the semester.
*/
case class TopicGroup (entries : Vector[TopicEntry] ) extends LogSupport {

  /**  Extract heading for this unit, if it exists.
  */
  def heading: Option[SectionTopic] = {
    entries(0) match {
      case day: CourseDay => None
      case hdg : SectionTopic => Some(hdg)
    }
  }

  /** Number of entries. */
  def size: Int = entries.size

  /** Cluster entries into groups organized by [[TopicEntry]]s
  */

  def clusters: Vector[TopicGroup] = {
    val noEntries = Vector.empty[TopicGroup]
    debug("TopicGroup instance clustering " + entries.size + " TopicEntrys.")

    addCluster(entries, noEntries )
  }


  /** Break of a set of [[TopicEntry]]s into a series of [[TopicGroup]] broken up
  * by [[SectionTopic]]s.
  */
  def addCluster(src: Vector[TopicEntry], target: Vector[TopicGroup]) : Vector[TopicGroup] = {
    // SO THIS LOOKS GOOD:
    //Logger.setDefaultLogLevel(LogLevel.DEBUG)
    debug("TopicGroup, addCluster: src size " + src.size)
    debug("<-" + src.mkString("\n<-"))
    debug("TopicGroup, addCluster: target size " + target.size)
    debug("=>" + target.map(tg => tg.entries.mkString("=>")).mkString("\n"))
    //Logger.setDefaultLogLevel(LogLevel.INFO)

      if (src.isEmpty) {
        target
      } else {

        val nextClusterSequence: Vector[TopicEntry] = TopicGroup.nextCluster(src, Vector.empty[TopicEntry] )
        val newTopicGroup = TopicGroup(nextClusterSequence)

        val newSrc = src.drop(nextClusterSequence.size)
        val newTarget = target :+ newTopicGroup
        //Logger.setDefaultLogLevel(LogLevel.DEBUG)
        debug("next cluster has " + nextClusterSequence.size + " topics.")
        debug("<-" + nextClusterSequence.mkString("\n<-"))
        //Logger.setDefaultLogLevel(LogLevel.INFO)
        val updated = addCluster(newSrc,newTarget)

        //Logger.setDefaultLogLevel(LogLevel.DEBUG)
        debug("**updated cluster list**:  size now " + updated.size )
        debug("=>" + updated.map(_.entries.mkString("\n=>")))
        //Logger.setDefaultLogLevel(LogLevel.INFO)

        updated
      }



      /*
    if (src.isEmpty) {
      //Logger.setDefaultLogLevel(LogLevel.DEBUG)
      debug("TopicGroup, addcluster complete with " + target.filter(_.entries.nonEmpty).size + " entries")
      debug(target)
      //Logger.setDefaultLogLevel(LogLevel.INFO)


      target.filter(_.entries.nonEmpty)

    } else {
      val nextCluster: Vector[TopicEntry] = TopicGroup.nextCluster(src, Vector.empty[TopicEntry] )

      if (target.size == 0) {
        addCluster(src.drop(nextCluster.size), Vector(TopicGroup(nextCluster)))
      } else {
        val composite =   target ++  Vector(TopicGroup(nextCluster))
          //Logger.setDefaultLogLevel(LogLevel.DEBUG)
          debug("TopicGroup, next cluster: " + nextCluster)
          debug("NEW COMPOSITE:\n-->" + composite.mkString("\n-->"))
          //Logger.setDefaultLogLevel(LogLevel.INFO)
        addCluster(src.drop(nextCluster.size), composite)
      }
    } */
  }


  /** Cluster a series of [[Week]]s by clusters
  * labelled with headings.
  *
  * @param classesPerWeek Number of times class meets weekly.
  */
  def weeklyClustered(classesPerWeek: Int): Vector[Vector[Week]]  = {
    clusters.map(_.weekly(classesPerWeek))
  }


  /** Cluster a sequence of [[TopicEntry]]s in weekly
  * sequences.
  *
  * @param classesPerWeek Number of class meetings per Week.
  */
  def weekly(classesPerWeek: Int):  Vector[Week]  = {
      val clustered = Vector.empty
      addWeek(days, clustered, classesPerWeek)
  }


  /** Recursively cluster [[TopicEntry]]s in weekly sequences by
  * adding the next week of [[TopicEntry]]s to a list.
  *
  * @param classesPerWeek Number of class meetings per Week.
  */
  def addWeek(source: Vector[TopicEntry],
    clustered: Vector[Week],
    classesPerWeek: Int):  Vector[Week] = {

    if (source.size >= classesPerWeek) {
      val week = Week(source.take(classesPerWeek), classesPerWeek)
      val appended = clustered ++ Vector(week)
      addWeek(source.drop(classesPerWeek),appended, classesPerWeek)
    } else {
      if (source.size > 0) {
        warn(s"WE HAVE ${source.size} DANGLING RECORDS")
      }
      clustered
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
  def days: Vector[TopicEntry] = entries.filter(TopicGroup.isDay(_))
}



/** Factory object for creating a [[TopicGroup]] from a
* delimited-text file.
*/
object TopicGroup {
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

  /** Create a [[TopicGroup]] from a delimited-text file.
  *
  * @param syllabusFileName Name of file with sequence of course topics.
  */
  def apply(syllabusFileName: String): TopicGroup = {
    val hdr = "^#.+".r
    val lns = Source.fromFile(syllabusFileName).getLines().toVector.filter(_.nonEmpty)
    val entries = lns.map( l =>
      hdr.findFirstIn(l) match {
        case None =>  CourseDay(l)
        case h: Some[String] => Some(SectionTopic(l))
      }
    )
    TopicGroup(entries.map(_.get))
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
  def nextCluster(src: Vector[TopicEntry], target: Vector[TopicEntry]): Vector[TopicEntry] = {
    if (src.isEmpty) {
      target
    } else {
      val nextVal = src(0)
      nextVal match {
        case day : CourseDay => {
          nextCluster(src.drop(1), target ++ Vector(day)  )
        }
        case topic : SectionTopic => {
          // accept section heading at beginning of cluster
          if (target.size == 0) {
            nextCluster(src.drop(1), Vector(topic))
          } else {
            target
          }
        }
      }
    }
  }
}
