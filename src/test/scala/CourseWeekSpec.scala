

package edu.holycross.shot.coursecal

import org.scalatest.FlatSpec
import java.time._
import java.time.format._

class CourseWeekSpec  extends FlatSpec {


  val startDate = LocalDate.parse("2017-08-30")

  "A CourseWeek type object" should "find two dates for a TTh schedule" in {

    val expectedTues = LocalDate.parse("2017-08-29")
    val expectedThurs = LocalDate.parse("2017-08-31")
    val tth = TuThWeek(startDate)

    assert(tth.tues == expectedTues)
    assert(tth.thurs == expectedThurs)
   }
}
