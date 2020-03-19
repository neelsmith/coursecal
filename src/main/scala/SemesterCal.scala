package edu.holycross.shot.coursecal

import java.time._
import java.time.temporal._
import java.util.Locale
import java.time.format._


/** A Vector of [[DatedWeekMeetings]]s. */
trait Semester {
  def weeks : Vector[_ <: DatedWeekMeetings]
}

/**  DatedWeekMeetingss on a TTh pattern.
*
* @param startDate A date in the first week of classes.
* @param totalWeeks Number of calendar weeks to schedule.
*/
case class TuesThursSemester(startDate: LocalDate, totalWeeks: Int) extends Semester {
  def weeks = {
    def wks = for (i <- 0 until totalWeeks) yield {
      val currReferenceDate = startDate.plusWeeks(i)
      TuThWeek(currReferenceDate)
    }
    wks.toVector
  }
}


/**  DatedWeekMeetingss on a MWF pattern.
*
* @param startDate A date in the first week of classes.
* @param totalWeeks Number of calendar weeks to schedule.
*/
case class MonWedFriSemester(startDate: LocalDate, totalWeeks: Int) extends Semester  {
  def weeks = {
    def wks = for (i <- 0 until totalWeeks) yield {
      val currReferenceDate = startDate.plusWeeks(i)
      MonWedFriWeek(currReferenceDate)
    }
    wks.toVector
  }
}



/**  DatedWeekMeetingss on a WF pattern.
*
* @param startDate A date in the first week of classes.
* @param totalWeeks Number of calendar weeks to schedule.
*/
case class WedFriSemester(startDate: LocalDate, totalWeeks: Int) extends Semester {
  def weeks = {
    def wks = for (i <- 0 until totalWeeks) yield {
      val currReferenceDate = startDate.plusWeeks(i)
      WedFriWeek(currReferenceDate)
    }
    wks.toVector
  }
}
