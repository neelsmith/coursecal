/*
This file has a group of *topic*, or *content* objects.
*/

package io.github.neelsmith
package  coursecal

import scala.io.Source
import ammonite.ops._
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap



class Syllabus (syllabusPath: FilePath ) {
  def getEntryArray() = {
    val syllabusFile = resolveFileRef(syllabusPath)
    getEntriesForSyllabus(syllabusFile)
  }
}


trait SyllabusEntry {

}

// content entry for one class meeting
case class CourseDay(val title: String, val assignment: String, val notes: String, val tags: Array[String] ) extends SyllabusEntry {
  override def toString() = {
    "Daily topic: " + title
  }
}

// section label
case class SectionTopic(level: Int,title: String) extends SyllabusEntry {
  override def toString() = {
    "Section: " + title
  }
}
