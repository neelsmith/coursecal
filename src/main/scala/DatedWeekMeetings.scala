package edu.holycross.shot.coursecal

import java.time._
import java.time.temporal._
import java.util.Locale
import java.time.format._


/** Classes mapping a weekly schedule pattern on to specific
* dates for a single week.
*/
sealed abstract class DatedWeekMeetings {
  def dates: Vector[LocalDate]
}

/**  Class dates for a TTh schedule pattern in a single
* week.
*
* @param oneDay A single day in the week (any day) for
* which classes meet on Tues and Thurs.
*/
case class TuThWeek(val oneDay: LocalDate) extends DatedWeekMeetings {
  val tues = oneDay.`with`(DayOfWeek.TUESDAY)
  val thurs = oneDay.`with`(DayOfWeek.THURSDAY)

  def dates : Vector[LocalDate] = {
    Vector(tues, thurs)
  }
  override def toString() = {
    "TuThWeek " + tues + ", " + thurs
  }
}


/**  Class dates for a MWF schedule pattern in a single
* week.
*
* @param oneDay A single day in the week (any day) for
* which classes meet on Mon, Wed and Fri.
*/
case class MonWedFriWeek(val oneDay: LocalDate) extends DatedWeekMeetings {
  val mon = oneDay.`with`(DayOfWeek.MONDAY)
  val wed = oneDay.`with`(DayOfWeek.WEDNESDAY)
  val fri = oneDay.`with`(DayOfWeek.FRIDAY)

  def dates : Vector[LocalDate] = {
    Vector(mon, wed, fri)
  }

  override def toString() = {
    "MWFWeek " + mon + ", " + wed + ", " + fri
  }
}


/**  Class dates for a WF schedule pattern in a single
* week.
*
* @param oneDay A single day in the week (any day) for
* which classes meet on Wed and Fri.
*/
case class WedFriWeek(val oneDay: LocalDate) extends DatedWeekMeetings {
  val wed = oneDay.`with`(DayOfWeek.WEDNESDAY)
  val fri = oneDay.`with`(DayOfWeek.FRIDAY)


  def dates : Vector[LocalDate] = {
    Vector(wed, fri)
  }

  override def toString() = {
    "WFWeek " + wed + ", " + fri
  }
}
