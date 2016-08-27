package io.github.neelsmith


/*
This is a group of calendrical objects.
*/
import java.time._
import java.time.temporal._
import java.util.Locale
import java.time.format._

import scala.collection.mutable.ArrayBuffer
/*
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

}*/
