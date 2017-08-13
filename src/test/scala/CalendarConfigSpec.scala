

package edu.holycross.shot.coursecal

import org.scalatest.FlatSpec
import java.time._
import java.time.format._

class CalendarConfigSpec  extends FlatSpec {

  val cal = CalendarConfig("src/test/resources/ica.yaml")

  "A CalendarConfig" should "have a title" in {
     val expectedTitle = "Introduction to Classical Archaeology, F'16: course schedule"
     assert(cal.title == expectedTitle)
   }

   it should "define a starting date" in {
     val expectedDate = LocalDate.parse("2016-08-25")
     assert(cal.weekOne == expectedDate)
  }

  it should "define a schedule type" in {
    val expectedType = TTh
    assert(cal.scheduleType == expectedType)
  }

  it should "define the total number of *calendar* weeks the course will run" in {
    val expectedWeeks = 15
    assert(cal.calendarWeeks == expectedWeeks)
  }

  it should "have a Vector of events with fixed dates" in {
    val expectedEvents = 4
    assert(cal.fixedEvents.size == expectedEvents)

    for (evt <- cal.fixedEvents) {
      evt match {
        case fixed : FixedEvent => assert(true)
        case _ => fail("Vector should only contain FixedEvent objects")
      }
    }
  }
}
