package io.github.neelsmith


  import java.time._
  import java.time.temporal._
  import java.util.Locale
  import java.time.format._
/*
This is a group of calendrical objects.
*/

package object coursecal {

  def calendarForConfig(conf: CalendarConfig) = {
    conf.sched.toLowerCase() match {
      case "mwf" => MonWedFriSemester(conf.firstDay, conf.totalWeeks)
      case "monwedfri" => MonWedFriSemester(conf.firstDay, conf.totalWeeks)
      case "tt" => TuesThursSemester(conf.firstDay, conf.totalWeeks)
      case "tth" => TuesThursSemester(conf.firstDay, conf.totalWeeks)
      case "tuesthurs" => TuesThursSemester(conf.firstDay, conf.totalWeeks)
      //case _ =>
    }
  }

    def shortDisplayWeek(wk: CourseWeek) = {
      wk match {
        case wk: TuThWeek =>  "Tues., " + shortDisplayDay(wk.tues) + "\tThurs., " + shortDisplayDay(wk.thurs)
        case wk: MonWedFriWeek =>  "Mon., " + shortDisplayDay(wk.mon) + "\tWed., " + shortDisplayDay(wk.wed) + "\tFri., " + shortDisplayDay(wk.fri)
      }
    }

    // Format a readable short string to display a date
    def shortDisplayDay(d: LocalDate) =  {
      d.getMonth.getDisplayName(TextStyle.SHORT,Locale.US) + ". " + d.getDayOfMonth()
    }

}



package coursecal {


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

}
