

package edu.holycross.shot.coursecal

import org.scalatest.FlatSpec
import java.time._
import java.time.format._

class CoinsSpec  extends FlatSpec {

  val topics = "src/test/resources/coins.txt"
  val conf = "src/test/resources/coins.yaml"
  val sched = Schedule(topics, conf)

  "The Schedule object" should "create a Schedule from a pair of files" in {
    sched match {
      case s: Schedule => assert(true)
      case _ => fail("Should have created a Schedule")
    }
  }

  it should "format a segment of the topics list as a markdown table" in {
      val md = sched.markdownCalendar
      println(md)
      println("Formatted md")
  }


}
