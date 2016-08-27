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


/** Resolves an Ammonite FilePath to an absolute Path.
*
* If fp is relative, it is assumed to be relative to
* the ammonite working directory in cwd.
*
* @param fp FilePath object to resolve.
* @return A Path object with absolute path.
*/
def resolveFileRef(fp: FilePath): Path = {
  fp match {
      case fp: RelPath => Path(fp, cwd)
      case fp: Path => fp
    }
}

/** Creates a semester calendar from the information in a
* semester calendar configuration.
*
* @param conf Configuration of the calendar.
* @return A semester calendar corresponding to the configuration.
*/
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

/** Creates a CourseDay object from one line of text
* from a syllabus file.
*
* Lines should be formatted as three columns delimited by '#'.
*
* @param ln The line of text.
* @return A CourseDay, or None.
*/
def courseDayForLine(ln: String ): Option[CourseDay] = {
  val cols = ln.split("#")
  cols.size match {
    case 3 =>  Some(CourseDay(cols(0),cols(1),cols(2),Array("")))
    case 2 =>  Some(CourseDay(cols(0),cols(1),"",Array("")))
    case 1 => Some(CourseDay(cols(0),"","",Array("")))
    case _ => { println("Error for line '" + ln + "', found " + cols.size + " columns.")
      None
    }
  }

}

/** Creates an array of syllabus entries from a
* syllabus file identified by Path.
*
* Syllabus entries may either be daily class entries,
* or labelling headings for sections of the course.
*
* @param syll Absoute path to the syllabus file.
* @return Array of syllabus etnries.
*/
def getEntriesForSyllabus(syll: Path): ArrayBuffer[SyllabusEntry] = {
  val lns = read.lines!(syll)

  val hdrPattern = "^#+(.+)".r
  val emptyLine = "^$".r
  val nonEmpty = "([^#].+)".r

  var entryArray = new ArrayBuffer[SyllabusEntry]
  for (ln <- lns) {

    ln match  {
    case emptyLine(ln) => println("Line was empty")
    case hdrPattern(ln) => {entryArray += SectionTopic(0,ln)}

    case  nonEmpty(ln) => {
      val oneDay = courseDayForLine(ln)
      oneDay match {
        case Some(CourseDay(_,_,_,_)) => {
          //println("Add " + oneDay.get + " to entries array")
          entryArray += oneDay.get
        }
        case _ => { println("Failed to get a CourseDay from line " + ln)
          println ("Got " + oneDay)
        }
      }
    }
    case _ => //println("Line '" + ln + "' (length " + ln.size + ") matched no pattern!")
    }
  }
  entryArray
}
}
