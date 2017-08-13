

package edu.holycross.shot.coursecal

import org.scalatest.FlatSpec
import java.time._
import java.time.format._

class MonWedFriSemesterSpec  extends FlatSpec {

  val startDate = LocalDate.parse("2017-08-30")

  "A MonWedFriSemester" should "create a vector of MonWedFri weeks" in {
    val mwfSemester = MonWedFriSemester(startDate, 15)
    val expectedSize = 15
    assert(mwfSemester.weeks.size == expectedSize)

    val firstDay = mwfSemester.weeks(0).mon
    val expectedFirstDay = LocalDate.parse("2017-08-28")
    assert (firstDay == expectedFirstDay)


    val expectedLastDay = LocalDate.parse("2017-12-08")
    val lastDay = mwfSemester.weeks(14).fri
    assert(lastDay == expectedLastDay)
  }




}
