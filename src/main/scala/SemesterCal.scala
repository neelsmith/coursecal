package edu.holycross.shot.coursecal

  import java.time._
  import java.time.temporal._
  import java.util.Locale
  import java.time.format._
/*
This is a group of calendrical objects.
*/






  import scala.collection.mutable.ArrayBuffer

// Calendar for a Semester is a list of CourseWeeks.
// CourseWeeks are either TuThuWeek or MonWedFriWeek objects.


//sealed abstract class Semester {
trait Semester {
  def weeks : ArrayBuffer[_ <: CourseWeek]
}

case class TuesThursSemester(startDate: LocalDate, totalWeeks: Int) extends Semester {
  val weeks = new ArrayBuffer[TuThWeek]
  var i = 0
  for (i <- 0 to totalWeeks) {
    val currReferenceDate = startDate.plusWeeks(i)
    val wk = new TuThWeek(currReferenceDate)
    weeks += wk
  }
  //def weeks : ArrayBuffer[TuThWeek] = { wks }
}

case class MonWedFriSemester(startDate: LocalDate, totalWeeks: Int) extends Semester {
  val weeks = new ArrayBuffer[MonWedFriWeek]
  var i = 0
  for (i <- 0 to totalWeeks) {
    val currReferenceDate = startDate.plusWeeks(i)
    val wk = new MonWedFriWeek(currReferenceDate)
    weeks += wk
  }
  //def weeks : ArrayBuffer[MonWedFriWeek] = { wks }
}
