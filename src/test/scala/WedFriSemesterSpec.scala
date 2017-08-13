

package edu.holycross.shot.coursecal

import org.scalatest.FlatSpec
import java.time._
import java.time.format._

class WedFriSemesterSpec  extends FlatSpec {

  val startDate = LocalDate.parse("2017-08-30")

  "A WedFriSemester" should "create a vector of WedFri weeks" in {
    val wfSemester = WedFriSemester(startDate, 15)
    val expectedSize = 15
    assert(wfSemester.weeks.size == expectedSize)

    val firstDay = wfSemester.weeks(0).wed
    val expectedFirstDay = LocalDate.parse("2017-08-30")
    assert (firstDay == expectedFirstDay)


    val expectedLastDay = LocalDate.parse("2017-12-08")
    val lastDay = wfSemester.weeks(14).fri
    assert(lastDay == expectedLastDay)
  }




}
