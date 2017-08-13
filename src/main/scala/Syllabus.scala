
//This file has a group of *topic*, or *content* objects.

package edu.holycross.shot.coursecal

import scala.io.Source
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap


/** A syllabus of topics for a course.
*
* @param entries Sequence of [[SyllabusEntry]]s for the semester.
*/
case class Syllabus (entries : Vector[SyllabusEntry] )


/** Factory object for creating a [[Syllabus]] from a
* delimited-text file.
*/
object Syllabus {

  /** Create a [[Syllabus]] from a delimited-text file.
  *
  * @param syllabusFileName Name of file with sequence of course topics.
  */
  def apply(syllabusFileName: String): Syllabus = {
    val hdr = "^#.+".r
    val lns = scala.io.Source.fromFile(syllabusFileName).getLines().toVector.filter(_.nonEmpty)
    println("LINES: " + lns)
    val entries = lns.map( l =>
      hdr.findFirstIn(l) match {
        case None =>  CourseDay(l)
        case h: Some[String] => Some(SectionTopic(l))
      }
    )
    Syllabus(entries.map(_.get))
  }



}
