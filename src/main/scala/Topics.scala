package edu.holycross.shot.coursecal

import scala.io.Source
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap


/** A syllabus of topics for a course.
*
* @param entries Sequence of [[TopicEntry]]s for the semester.
*/
case class Topics (entries : Vector[TopicEntry] ) {




  /** Extract [[CourseDay]] entries from the list.
  */
  def days: Vector[TopicEntry] = entries.filter(Topics.isDay(_))
}



/** Factory object for creating a [[Topics]] from a
* delimited-text file.
*/
object Topics {

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
    def isDay(entry: TopicEntry) : Boolean  =
      entry match {
        case day: CourseDay => true
        case _ => false
      }


}
