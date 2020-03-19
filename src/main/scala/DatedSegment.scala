package edu.holycross.shot.coursecal


import wvlet.log._
import wvlet.log.LogFormatter.SourceCodeLogFormatter

/** A segment of a full-term schedule with topics mapped on to calendar weeks.
*
* @param weeks Mapping of topics on to calendar weeks for this segment of the course.
* @param initialWeek Count of first week of this segment within course as a whole.
* @param heading Optional heading for this section of the course.
*/
case class DatedSegment(weeks : Vector[DatedWeek], initialWeek: Int, heading: Option[SectionTopic]) extends LogSupport {
  //Logger.setDefaultLogLevel(LogLevel.DEBUG)
  debug("New DatedSegment instance starts with week " + initialWeek + " of course.")

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
  //Logger.setDefaultLogLevel(LogLevel.INFO)
}
