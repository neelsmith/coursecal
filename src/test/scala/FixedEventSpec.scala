package edu.holycross.shot.coursecal

import org.scalatest.FlatSpec
import java.time._
import java.time.format._

class FixedEventSpec  extends FlatSpec {

  "A FixedEvent" should "report whether it falls on a day of a CourseWeek" in {
    val wed = LocalDate.parse("2017-08-30")
    val fixedDate = FixedEvent(wed, "First day of classes (Wed).")
    val mwf = MonWedFriWeek(wed)
    assert(fixedDate.inWeek(mwf))
  }

}
