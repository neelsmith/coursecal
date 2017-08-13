package edu.holycross.shot.coursecal

import java.time._
import java.time.temporal._
import java.util.Locale
import java.time.format._





import scala.collection.mutable.ArrayBuffer

/** A Vector of [[CourseWeeks]]. */
trait Semester {
  def weeks : Vector[_ <: CourseWeek]
}

/**  CourseWeeks on a TuesThurs pattern.
*
* @param startDate A date in the first week of classes.
* @param totalWeeks Number of calendar weeks to schedule.
*/
case class TuesThursSemester(startDate: LocalDate, totalWeeks: Int) extends Semester {
  def weeks = {
    def wks = for (i <- 0 until totalWeeks) yield {
      val currReferenceDate = startDate.plusWeeks(i)
      TuThWeek(currReferenceDate)
    }
    wks.toVector
  }
  /*
  val weeks = new ArrayBuffer[TuThWeek]
  var i = 0
  for (i in  0 until totalWeeks) {
    val currReferenceDate = startDate.plusWeeks(i)
    val wk = new TuThWeek(currReferenceDate)
    weeks += wk
  }*/
  //def weeks : ArrayBuffer[TuThWeek] = { wks }
}

case class MonWedFriSemester(startDate: LocalDate, totalWeeks: Int)  {
  val weeks = new ArrayBuffer[MonWedFriWeek]
  var i = 0
  for (i <- 0 to totalWeeks) {
    val currReferenceDate = startDate.plusWeeks(i)
    val wk = new MonWedFriWeek(currReferenceDate)
    weeks += wk
  }
  //def weeks : ArrayBuffer[MonWedFriWeek] = { wks }
}
