

package edu.holycross.shot.coursecal

import org.scalatest.FlatSpec
import java.time._
import java.time.format._

class TuesThursSemesterSpec  extends FlatSpec {

  val startDate = LocalDate.parse("2017-08-30")

  "A TuesThursSemester" should "create a vector of TuesThurs weeks" in {
    val tthSemester = TuesThursSemester(startDate, 15)
    val expectedSize = 15
    assert(tthSemester.weeks.size == expectedSize)

    val firstDay = tthSemester.weeks(0).tues
    val expectedFirstDay = LocalDate.parse("2017-08-29")
    assert (firstDay == expectedFirstDay)


    val expectedLastDay = LocalDate.parse("2017-12-07")
    val lastDay = tthSemester.weeks(14).thurs
    assert(lastDay == expectedLastDay)
  }




}
