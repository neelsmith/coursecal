/*
Ideas:

- get a starting date
- generate a series of week objects from that
- parse a file and collect a series of syllabus entries
- map them together to a calendar

*/

// jvm data-time is a nightmare.
import java.time._
import java.time.temporal._


import $file.SemesterCal, SemesterCal._

// First date of semester
val startDate = LocalDate.of(2016,8,29)

// length of semester at HC:
val totalWeeks = 15

val ttSemester = TuesThurs(startDate, totalWeeks)
val mwfSemester = MonWedFri(startDate, totalWeeks)



println ("For semester beginning " + shortDisplayDay(startDate))
println("TTh schedule is:\n")
for (i <- 0 until ttSemester.wks.length) {
  println (shortDisplayWeek(ttSemester.wks(i)))
}
println("\nMWF schedule is: \n")
for (i <- 0 until mwfSemester.wks.length) {
  println (shortDisplayWeek(mwfSemester.wks(i)))
}
