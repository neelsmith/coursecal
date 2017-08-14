package edu.holycross.shot.coursecal

import java.time._
import java.time.format._

/**  Events with predefined dates that cannot be moved.
*
* @param eventDate Fixed date of event.
* @param eventLabel Label of event.
*/
class FixedEvent(val eventDate: LocalDate, val eventLabel: String) {

  override def toString = {
    "Fixed event " + eventLabel + " on " + eventDate
  }
}
