/*
  Create a syllabus and traverse its entries:
*/

import ammonite.ops._
import java.time._
import java.time.temporal._


import $file.SemesterCal, SemesterCal._
import $file.CourseTopics, CourseTopics._

// length of semester at HC:
val totalWeeks = 15



@main
def main(yr: Int, mo: Int, d: Int, sched: String, syll: String) = {
  val syllabus = new Syllabus(FilePath(syll))
  val startDate = LocalDate.of(2016,8,29)
  println ("For semester starting " + shortDisplayDay(startDate))
  println("Map this content:")
  for (oneDay <- syllabus.entryArray) {
    println(oneDay)
  }

  val ttSemester = TuesThurs(startDate, totalWeeks)
  val mwfSemester = MonWedFri(startDate, totalWeeks)


  println("to the schedule for " + sched)

  sched.toLowerCase() match {
    case "tt" => shortDisplaySemester(ttSemester)
    case "mwf" => shortDisplaySemester( mwfSemester)
  }

}
