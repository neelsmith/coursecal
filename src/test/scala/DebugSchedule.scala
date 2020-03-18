

package edu.holycross.shot.coursecal

import org.scalatest.FlatSpec
import java.time._
import java.time.format._

class DebugSchedule  extends FlatSpec {

  val topics = "src/test/resources/ancsci-topics2.txt"
  val conf = "src/test/resources/ancsci2.yaml"

  "The Schedule object" should "work with this stuff" in pending /*{
    val sched = Schedule(topics, conf)
    sched match {
      case s: Schedule => assert(true)
      case _ => fail("Should have created a Schedule")
    }
  }*/

  "A Schedule instance" should "make proper markdown for a calendar" in {
    val sched = Schedule(topics, conf)
    val md = sched.markdownCalendar
  }


}
