package edu.holycross.shot.coursecal

import scala.io.Source
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap


trait SyllabusEntry {
  //def getComponents?
}

/** Content entry for one class meeting.
*
* @param title Title for meeting.
* @param notes Notes
* @param tags Tags
*/
case class CourseDay(title: String, notes: String, tags: Array[String] ) extends SyllabusEntry {
  override def toString() = {
    if (notes.size > 0 ) {
      "Daily topic: " + title + " (notes: " + notes + ")"
    } else {
    "Daily topic: " + title + " (no notes)"
    }
  }
}

/** Label for section heading.
*
* @param level Heading level.
* @param title Title of section.
*/
case class SectionTopic(level: Int,title: String) extends SyllabusEntry {
  override def toString() = {
    "Section: " + title
  }

}
