package edu.holycross.shot


/*
This is a group of calendrical objects.
*/

package object coursecal {


//import ammonite.ops._
import scala.io.Source
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

//
/*
def resolveFileRef(fp: FilePath): Path = {
  fp match {
      case fp: RelPath => Path(fp, cwd)
      case fp: Path => fp
    }
}*/

def shortDisplayWeek(wk: CourseWeek) = {
  wk match {
    case wk: TuThWeek =>  "Tues., " + shortDisplayDay(wk.tues) + "\tThurs., " + shortDisplayDay(wk.thurs)
    case wk: MonWedFriWeek =>  "Mon., " + shortDisplayDay(wk.mon) + "\tWed., " + shortDisplayDay(wk.wed) + "\tFri., " + shortDisplayDay(wk.fri)
    case wk: WedFriWeek =>  "Wed., " + shortDisplayDay(wk.wed) + "\tFri., " + shortDisplayDay(wk.fri)
  }
}

// Format a readable short string to display a date
def shortDisplayDay(d: LocalDate) =  {
  d.getMonth.getDisplayName(TextStyle.SHORT,Locale.US) + ". " + d.getDayOfMonth()
}

/** Creates a CourseDay object from one line of text
* from a syllabus file.
*
* Lines should be formatted as three columns delimited by '#'
* representing a title or description, a link to an assignment, and
* weekly notes or reminders.  The third column is optional; the
* second is optional if there is no third column.
* @param ln The line of text.
* @return A CourseDay, or None.
*/
def courseDayForLine(ln: String ): Option[CourseDay] = {
  val cols = ln.split("#")
  cols.size match {
    //case 3 =>  Some(CourseDay(cols(0),cols(1),cols(2),Array("")))
    case 2 =>  Some(CourseDay(cols(0),cols(1),Array("")))
    case 1 => Some(CourseDay(cols(0),"",Array("")))
    case _ => { println("Error for line '" + ln + "', found " + cols.size + " columns.")
      None
    }
  }

}

} // package
