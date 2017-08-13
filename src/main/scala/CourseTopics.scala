
//This file has a group of *topic*, or *content* objects.

package edu.holycross.shot.coursecal

import scala.io.Source
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap

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
