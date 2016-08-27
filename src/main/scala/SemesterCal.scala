package io.github.neelsmith


/*
This is a group of calendrical objects.
*/
package coursecal

  import java.time._
  import java.time.temporal._
  import java.util.Locale
  import java.time.format._

  import scala.collection.mutable.ArrayBuffer

// Calendar for a Semester is a list of CourseWeeks.
// CourseWeeks are either TuThuWeek or MonWedFriWeek objects.


sealed abstract class Semester {
  def getWeeks(): ArrayBuffer[_ <: CourseWeek]
}

case class TuesThursSemester(startDate: LocalDate, totalWeeks: Int) extends Semester {
  val wks = new ArrayBuffer[TuThWeek]
  var i = 0
  for (i <- 0 to totalWeeks) {
    val currReferenceDate = startDate.plusWeeks(i)
    val wk = new TuThWeek(currReferenceDate)
    wks += wk
  }
  def getWeeks(): ArrayBuffer[TuThWeek] = { wks }
}

case class MonWedFriSemester(startDate: LocalDate, totalWeeks: Int) extends Semester {
  val wks = new ArrayBuffer[MonWedFriWeek]
  var i = 0
  for (i <- 0 to totalWeeks) {
    val currReferenceDate = startDate.plusWeeks(i)
    val wk = new MonWedFriWeek(currReferenceDate)
    wks += wk
  }
  def getWeeks(): ArrayBuffer[MonWedFriWeek] = { wks }
}



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
