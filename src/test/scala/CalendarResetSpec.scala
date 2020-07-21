

package edu.holycross.shot.coursecal

import org.scalatest.FlatSpec
import java.time._
import java.time.format._

class CalendarResetSpec  extends FlatSpec {

  val calconf = CalendarConfig("src/test/resources/latin101.yaml")

  "A CalendarConfig" should "have a title" in {
    val expected = "Latin 101, F'20: course schedule"
    assert(calconf.title == expected)
  }
  it should "know the starting date for the calendar" in {
    val expected = LocalDate.parse("2020-09-02")
    assert(calconf.weekOne == expected)
  }
  it should "choose Monday date if week one is reset to 0" in {
    val expected = LocalDate.parse("2020-08-31")
    assert(calconf.resetWeekOne(0).weekOne == expected)
  }

  it should "reset calendar start by week intetger" in {
    val expected = LocalDate.parse("2020-09-07")
    assert(calconf.resetWeekOne(1).weekOne == expected)
  }
}
