
//This file has a group of *topic*, or *content* objects.


package io.github.neelsmith
package  coursecal

import scala.io.Source
import ammonite.ops._
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap


/** A syllabus of topics for a course.
*
* @param syllabusPath Path to file with syllabus.
*/
class Syllabus (syllabusPath: FilePath ) {
  val entryArray =  getEntriesForSyllabus(resolveFileRef(syllabusPath))
}


trait SyllabusEntry {
  //def getComponents
}

// content entry for one class meeting
case class CourseDay(val title: String, val assignment: String, val notes: String, val tags: Array[String] ) extends SyllabusEntry {
  override def toString() = {
    "Daily topic: " + title
  }
  //def getComponents
}

// section label
case class SectionTopic(level: Int,title: String) extends SyllabusEntry {
  override def toString() = {
    "Section: " + title
  }
  //def getComponents
}
