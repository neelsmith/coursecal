

package edu.holycross.shot.coursecal

import org.scalatest.FlatSpec
import java.time._
import java.time.format._

class MWFSemesterSpec  extends FlatSpec {

  val startDate = LocalDate.parse("2020-09-02")

  "A MonWedFriSemester" should "create a vector of MonWedFri weeks" in {
    val mwfSemester = MonWedFriSemester(startDate, 15)
    val expectedSize = 15
    assert(mwfSemester.weeks.size == expectedSize)

    val firstDay = mwfSemester.weeks(0).wed
    val expectedFirstDay = LocalDate.parse("2020-09-02")
    assert (firstDay == expectedFirstDay)


    val expectedLastDay = LocalDate.parse("2020-12-09")
    val lastDay = mwfSemester.weeks(14).wed
    assert(lastDay == expectedLastDay)

  }




}
