

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

  "A Schedule" should "compute the number of weeks in the topics list" in {
    val topics = "src/test/resources/greek101.txt"
    val conf = "src/test/resources/greek101.yaml"
    val sched = Schedule(topics, conf)
    val expectedWeeks = 4
    assert(sched.topicsWeeks == expectedWeeks)
  }

  it should "weave match topics with calendar dates" in {
    val topics = "src/test/resources/greek101.txt"
    val conf = "src/test/resources/greek101.yaml"
    val sched = Schedule(topics, conf)
    val semester = sched.datedTopics
    for (wk <- semester) {
      println(wk.calendarString)
    }
    println("SIZE: " + semester.size)
  }

  it should "generate an appropirate YAML header for a ghpages md file" in {
    val topics = "src/test/resources/greek101.txt"
    val conf = "src/test/resources/greek101.yaml"
    val sched = Schedule(topics, conf)

    val expected = "---\nlayout: page\ntitle: \"Greek 101, F'17: course schedule\"\n---\n"
    assert (sched.ghpageYamlHeader == expected)

  }

  it should "format a segment of the topcis list as a markdown table" in {
    val topics = "src/test/resources/greek101.txt"
    val conf = "src/test/resources/greek101.yaml"
    val sched = Schedule(topics, conf)

    val segments = sched.weeklySegmented
    val seg1 = segments(0)
    println(sched.markdownSegment(seg1))
  }

}
