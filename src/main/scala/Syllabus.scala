
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
    val lns = scala.io.Source.fromFile(syllabusFileName).getLines()

    val hdrPattern = "^#+(.+)".r
    val emptyLine = "^$".r
    val nonEmpty = "([^#].+)".r

    var entryArray = new ArrayBuffer[SyllabusEntry]
    for (ln <- lns) {
      ln match  {
      case emptyLine(ln) => //println("Line was empty")

      case hdrPattern(ln) => {entryArray += SectionTopic(0,ln)}

      case  nonEmpty(ln) => {
        val oneDay = courseDayForLine(ln)
        oneDay match {
          case Some(CourseDay(_,_,_)) => {
            //println("Add " + oneDay.get + " to entries array")
            entryArray += oneDay.get
          }
          case _ => { //println("Failed to get a CourseDay from line " + ln)
            //println ("Got " + oneDay)
          }
        }
      }
      case _ => //println("Line '" + ln + "' (length " + ln.size + ") matched no pattern!")
      }
    }
    entryArray
    Syllabus(Vector.empty[SyllabusEntry])
  }
}
