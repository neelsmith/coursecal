package edu.holycross.shot.coursecal

import scala.io.Source
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap


/** A syllabus of topics for a course.
*
* @param entries Sequence of [[SyllabusEntry]]s for the semester.
*/
case class Topics (entries : Vector[TopicEntry] )


/** Factory object for creating a [[Syllabus]] from a
* delimited-text file.
*/
object Topics {

  /** Create a [[Syllabus]] from a delimited-text file.
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

}
