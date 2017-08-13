
//This file has a group of *topic*, or *content* objects.
package edu.holycross.shot
package  coursecal

import scala.io.Source
//import ammonite.ops._
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap


/** A syllabus of topics for a course.
*
* @param syllabusFileName Name of file with syllabus.
*/
class Syllabus (val syllabusFileName: String ) {

  /** Creates an array of syllabus entries from a
  * syllabus file.
  *
  * Syllabus entries may either be daily class entries,
  * or labelling headings for sections of the course.
  *
  * @param syll Absoute path to the syllabus file.
  * @return Array of syllabus etnries.
  */
  def entries : ArrayBuffer[SyllabusEntry] = {
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
  }
}


trait SyllabusEntry {
  //def getComponents?
}

// content entry for one class meeting
case class CourseDay(val title: String, val notes: String, val tags: Array[String] ) extends SyllabusEntry {
  override def toString() = {
    if (notes.size > 0 ) {
      "Daily topic: " + title + " (notes: " + notes + ")"
    } else {
    "Daily topic: " + title + " (no notes)"
    }
  }
}

// section label
case class SectionTopic(level: Int,title: String) extends SyllabusEntry {
  override def toString() = {
    "Section: " + title
  }

}
