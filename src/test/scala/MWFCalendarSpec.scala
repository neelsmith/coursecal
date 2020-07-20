

package edu.holycross.shot.coursecal

import org.scalatest.FlatSpec
import java.time._
import java.time.format._

class MWFCalendarSpec  extends FlatSpec {

  val mwfcal = CalendarConfig("src/test/resources/latin101.yaml")

  "A CalendarConfig" should "have a title" in {
     val expectedTitle = "Latin 101, F'20: course schedule"
     assert(mwfcal.title == expectedTitle)
   }

   it should "define a starting date" in {
     val expectedDate = LocalDate.parse("2020-09-02")
     assert(mwfcal.weekOne == expectedDate)
  }

  it should "define a schedule type" in {
    val expectedType = MWF
    assert(mwfcal.scheduleType == expectedType)
  }

  it should "define the total number of *calendar* weeks the course will run" in {
    val expectedWeeks = 14
    assert(mwfcal.calendarWeeks == expectedWeeks)
  }

  it should "have a Vector of events with fixed dates" in pending /*{
    val expectedEvents = 4
    assert(calconf.fixedEvents.size == expectedEvents)

    for (evt <- calconf.fixedEvents) {
      evt match {
        case fixed : FixedEvent => assert(true)
        case _ => fail("Vector should only contain FixedEvent objects")
      }
    }
  }*/

  it should "find a calendar for the configured dates and course pattern" in {
    val cal = mwfcal.calendar
    cal match {
      case c: Some[Semester] => {
        c.get match {
          case mwf: MonWedFriSemester => assert(mwf.totalWeeks == 14)
          case _ => fail("Should have created a MWF semester")
        }
      }
      case None => fail("Should have created a Semester option")
    }
  }

  it should "find a week calendar for any week of the semester identified by zero-origin index" in  {
    val week1 = mwfcal.calForWeek(1)
    week1 match {
      case mwf: MonWedFriWeek => {
        val expectedWed = LocalDate.parse("2020-09-02")
        assert(mwf.wed == expectedWed)
      }
      case _ => fail("Should have created a MonWedFriWeek")
    }
  }
}
