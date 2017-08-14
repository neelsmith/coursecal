package edu.holycross.shot.coursecal

import java.time._
import java.time.format._

/**  Events with predefined dates that cannot be moved.
*
* @param eventDate Fixed date of event.
* @param eventLabel Label of event.
*/
case class FixedEvent(eventDate: LocalDate, eventLabel: String) {

  /** True if event falls on one of a course week's meeting days.
  *
  * @param courseWeek Week of dates to compare.
  */
  def inWeek(courseWeek: CourseWeek): Boolean = {
    courseWeek.dates.contains(eventDate)
  }

  override def toString = {
    "Fixed event " + eventLabel + " on " + eventDate
  }
}
