/*
*/
import java.time._
import java.time.temporal._
import java.util.Locale
import java.time.format._

import scala.collection.mutable.ArrayBuffer

import $file.CourseWeek, CourseWeek._

sealed abstract class Semester
case class TuesThurs(startDate: LocalDate, totalWeeks: Int) extends Semester {
  val wks = new ArrayBuffer[CourseWeek.TuThWeek]
  var i = 0
  for (i <- 0 to totalWeeks) {
    val currReferenceDate = startDate.plusWeeks(i)
    val wk = new CourseWeek.TuThWeek(currReferenceDate)
    wks += wk
  }
}

case class MonWedFri(startDate: LocalDate, totalWeeks: Int) extends Semester {
  val wks = new ArrayBuffer[CourseWeek.MonWedFriWeek]
  var i = 0
  for (i <- 0 to totalWeeks) {
    val currReferenceDate = startDate.plusWeeks(i)
    val wk = new CourseWeek.MonWedFriWeek(currReferenceDate)
    wks += wk
  }
}


def shortDisplaySemester(cal: Semester) {
  cal match {
    case cal: TuesThurs =>  for (wk <- cal.wks) {
      println (shortDisplayWeek(wk))
    }
    case cal: MonWedFri =>  for (wk <- cal.wks) {
      println (shortDisplayWeek(wk))
    }
  }
}

def shortDisplayWeek(wk: CourseWeek) = {
  wk match {
    case wk: CourseWeek.TuThWeek =>  "Tues., " + shortDisplayDay(wk.tues) + "\tThurs., " + shortDisplayDay(wk.thurs)
    case wk: CourseWeek.MonWedFriWeek =>  "Mon., " + shortDisplayDay(wk.mon) + "\tWed., " + shortDisplayDay(wk.wed) + "\tFri., " + shortDisplayDay(wk.fri)
  }
}

// Format a readable short string to display a date
def shortDisplayDay(d: LocalDate) =  {
  d.getMonth.getDisplayName(TextStyle.SHORT,Locale.US) + ". " + d.getDayOfMonth()
}
