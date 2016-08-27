package io.github.neelsmith


/*
This is a group of calendrical objects.
*/

package object coursecal {

import ammonite.ops._
import scala.collection.mutable.ArrayBuffer
import java.time._
import java.time.temporal._
import java.util.Locale
import java.time.format._



def resolveFileRef(fp: FilePath) = {
  fp match {
      case fp: RelPath => Path(fp, cwd)
      case fp: Path => fp
    }
}

def calendarForConfig(conf: CalendarConfig): Option[Semester] = {
  conf.sched.toLowerCase() match {
    case "mwf" => Some(MonWedFriSemester(conf.firstDay, conf.totalWeeks))
    //case "monwedfri" => MonWedFriSemester(conf.firstDay, conf.totalWeeks)
    case "tt" => Some(TuesThursSemester(conf.firstDay, conf.totalWeeks))
    //case "tth" => TuesThursSemester(conf.firstDay, conf.totalWeeks)
    //case "tuesthurs" => TuesThursSemester(conf.firstDay, conf.totalWeeks)
    case _ => None
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

def courseDayForLine(ln: String ): Option[CourseDay] = {
  val cols = ln.split("#")

  if (cols.size == 3 ) {
    Some(CourseDay(cols(0),cols(1),cols(2),Array("")))
  } else {
    println("Error: " + cols)
    None
  }
}


def getEntriesForSyllabus(syll: Path) = {
  val lns = read.lines!(syll)

  val hdrPattern = "^#+(.+)".r
  val emptyLine = "^$".r
  val nonEmpty = "([^#].+)".r

  var entryArray = new ArrayBuffer[SyllabusEntry]
  for (ln <- lns) {
    ln match  {
    case emptyLine(ln) => None
    case hdrPattern(ln) => {entryArray += SectionTopic(0,ln)}

    case  nonEmpty(ln) => {
      val oneDay = courseDayForLine(ln)
      oneDay match {
        case Some(CourseDay(_,_,_,_)) => {
          println("Add " + oneDay.get + " to entries array")
          entryArray += oneDay.get}
        case _ => None
      }
    }
    case _ => None
    }
  }
  entryArray
}
}
