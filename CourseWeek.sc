/*
*/
import java.time._
import java.time.temporal._
import java.util.Locale
import java.time.format._


sealed abstract class CourseWeek

// Given any day in a week, find the Tues and Thurs dates
class TuThWeek(val oneDay: LocalDate) extends CourseWeek {
  val tues = oneDay.`with`(DayOfWeek.TUESDAY)
  val thurs = oneDay.`with`(DayOfWeek.THURSDAY)
}

class MonWedFriWeek(val oneDay: LocalDate) extends CourseWeek {
  val mon = oneDay.`with`(DayOfWeek.MONDAY)
  val wed = oneDay.`with`(DayOfWeek.WEDNESDAY)
  val fri = oneDay.`with`(DayOfWeek.FRIDAY)
}
