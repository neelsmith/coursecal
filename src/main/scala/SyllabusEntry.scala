package edu.holycross.shot.coursecal

import scala.io.Source
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap


/** Item that can appear in a sequence of course topics.
*/
trait SyllabusEntry


/** Labelledsection heading.
*
* @param level Heading level.
* @param title Title of section.
*/
case class SectionTopic(level: Int,title: String) extends SyllabusEntry {
  override def toString() = {
    "Section: " + title
  }
}


/** Content entry for one class meeting.
*
* @param title Title for meeting.
* @param notes Notes
* @param tags Tags
*/
case class CourseDay(title: String, notes: String, tags: Vector[DayTag] ) extends SyllabusEntry {
  override def toString() = {
    if (notes.size > 0 ) {
      "Daily topic: " + title + " (notes: " + notes + ")"
    } else {
    "Daily topic: " + title + " (no notes)"
    }
  }
}


/** Factory object for creating [[CourseDay]]s from text source.
*/
object CourseDay {

  /** Create a CourseDay object from one line of delimited text.
  *
  * Lines should be formatted as two columns delimited by '#'.  The first
  * column is the entry for the day;  the second column is optional weekly
  * notes or reminders.
  *
  * @param ln The line of text.
  */
  def apply(ln: String ): CourseDay = {
    val cols = ln.split("#")
    cols.size match {
      //case 3 =>  Some(CourseDay(cols(0),cols(1),cols(2),Array("")))
      case 2 =>  CourseDay(cols(0),cols(1),Vector.empty[DayTag])
      case 1 => CourseDay(cols(0),"",Vector.empty[DayTag])
      case _ => { throw new Exception("Error for line '" + ln + "', found " + cols.size + " columns.")
      }
    }

  }
}
