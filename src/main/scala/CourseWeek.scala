package edu.holycross.shot.coursecal

import java.time._
import java.time.temporal._
import java.util.Locale
import java.time.format._


/** Classes mapping a weekly schedule pattern on to specific
* dates.
*/
sealed abstract class CourseWeek


/**  Class dates for a TTh schedule pattern in a single
* week.
*
* @param oneDay A single day in the week (any day) for
* which classes meet on Tues and Thurs.
*/
case class TuThWeek(val oneDay: LocalDate) extends CourseWeek {
  val tues = oneDay.`with`(DayOfWeek.TUESDAY)
  val thurs = oneDay.`with`(DayOfWeek.THURSDAY)

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
case class MonWedFriWeek(val oneDay: LocalDate) extends CourseWeek {
  val mon = oneDay.`with`(DayOfWeek.MONDAY)
  val wed = oneDay.`with`(DayOfWeek.WEDNESDAY)
  val fri = oneDay.`with`(DayOfWeek.FRIDAY)


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
case class WedFriWeek(val oneDay: LocalDate) extends CourseWeek {
  val wed = oneDay.`with`(DayOfWeek.WEDNESDAY)
  val fri = oneDay.`with`(DayOfWeek.FRIDAY)

  override def toString() = {
    "WFWeek " + wed + ", " + fri
  }
}
