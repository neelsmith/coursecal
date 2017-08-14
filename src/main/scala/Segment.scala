package edu.holycross.shot.coursecal

import scala.io.Source
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap

case class Segment(weeks : Vector[DatedWeek], initialIndex: Int, heading: Option[SectionTopic]) {

  def markdown(tableHeading: String): String = {
    val rows = weeks.map(_.calendarString).mkString
    val hdg = heading.getOrElse("")

    Vector(s"${hdg}\n", tableHeading, rows.mkString).mkString
  }
}
