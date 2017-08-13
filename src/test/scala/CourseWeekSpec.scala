

package edu.holycross.shot.coursecal

import org.scalatest.FlatSpec
import java.time._
import java.time.format._

class CourseWeekSpec  extends FlatSpec {

  val startDate = LocalDate.parse("2017-08-30")

  "A TuThWeek" should "find two dates for a TTh schedule" in {
    val tth = TuThWeek(startDate)

    val expectedTues = LocalDate.parse("2017-08-29")
    val expectedThurs = LocalDate.parse("2017-08-31")

    assert(tth.tues == expectedTues)
    assert(tth.thurs == expectedThurs)
   }


   "A MonWedFriWeek" should "find three dates for a MWF schedule" in {
     val mwf = MonWedFriWeek(startDate)

     val expectedMon = LocalDate.parse("2017-08-28")
     val expectedWed = LocalDate.parse("2017-08-30")
     val expectedFri = LocalDate.parse("2017-09-01")

     assert(mwf.mon == expectedMon)
     assert(mwf.wed == expectedWed)
     assert(mwf.fri == expectedFri)
    }


    "A WedFriWeek" should "find two dates for a WF schedule" in {
       val mwf = WedFriWeek(startDate)

       val expectedWed = LocalDate.parse("2017-08-30")
       val expectedFri = LocalDate.parse("2017-09-01")

       assert(mwf.wed == expectedWed)
       assert(mwf.fri == expectedFri)
    }
}
