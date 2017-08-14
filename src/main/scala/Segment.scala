package edu.holycross.shot.coursecal

import scala.io.Source
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap


/** A segment of a full-term schedule with topics mapped on to calendar weeks.
*
* @param weeks Mapping of topics on to calendar weeks for this segment of the course.
* @param initialWeek Count of first week of this segment with course as a whole.
* @param heading Optional heading for this section of the course.
*/
case class Segment(weeks : Vector[DatedWeek], initialIndex: Int, heading: Option[SectionTopic]) {


  /** Compose appropriate markdown to format the schedule
  * for this segment of a course as a calendar table.
  *
  * @param tableHeading Markdown heading for calendar table.
  * See values defined in the [[Schedule]] object.
  */
  def markdown(tableHeading: String): String = {
    val rows = weeks.map(_.calendarString).mkString
    val hdg = heading.getOrElse("")

    Vector(s"${hdg}\n", tableHeading, rows.mkString).mkString
  }
}
