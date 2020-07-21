package edu.holycross.shot.coursecal

import org.scalatest.FlatSpec
import java.time._
import java.time.format._

class CalendarConfigSpec  extends FlatSpec {

  val calconf = CalendarConfig("src/test/resources/ica.yaml")

  "A CalendarConfig" should "have a title" in {
     val expectedTitle = "Introduction to Classical Archaeology, F'16: course schedule"
     assert(calconf.title == expectedTitle)
   }

   it should "define a starting date" in {
     val expectedDate = LocalDate.parse("2016-08-25")
     assert(calconf.weekOne == expectedDate)
  }

  it should "define a schedule type" in {
    val expectedType = TTh
    assert(calconf.scheduleType == expectedType)
  }

  it should "define the total number of *calendar* weeks the course will run" in {
    val expectedWeeks = 15
    assert(calconf.calendarWeeks == expectedWeeks)
  }

  it should "have a Vector of events with fixed dates" in {
    val expectedEvents = 4
    assert(calconf.fixedEvents.size == expectedEvents)

    for (evt <- calconf.fixedEvents) {
      evt match {
        case fixed : FixedEvent => assert(true)
        case _ => fail("Vector should only contain FixedEvent objects")
      }
    }
  }

  it should "find a calendar for the configured dates and course pattern" in {
    val cal = calconf.calendar
    cal match {
      case c: Some[Semester] => {
        c.get match {
          case tth: TuesThursSemester => assert(tth.totalWeeks == 15)
          case _ => fail("Should have created a TTh semester")
        }
      }
      case None => fail("Should have created a Semester option")
    }
  }

  it should "find a week calendar for any week of the semester identified by zero-origin index" in {
      val calconf = CalendarConfig("src/test/resources/greek101.yaml")
      val week1 = calconf.calForWeek(1)
      week1 match {
        case mwf: MonWedFriWeek => {
          val expectedWed = LocalDate.parse("2017-09-06")
          assert(mwf.wed == expectedWed)
        }
        case _ => fail("Should have created a MonWedFriWeek")
      }
  }
}
