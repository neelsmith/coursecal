

package edu.holycross.shot.coursecal

import org.scalatest.FlatSpec
import java.time._
import java.time.format._

class ScheduleSpec  extends FlatSpec {

  "The Schedule object" should "create a Schedule from a pair of files" in {
    val topics = "src/test/resources/greek101.txt"
    val conf = "src/test/resources/ica.yaml"
    val sched = Schedule(topics, conf)
    sched match {
      case s: Schedule => assert(true)
      case _ => fail("Should have created a Schedule")
    }
  }

}
