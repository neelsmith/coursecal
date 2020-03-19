

package edu.holycross.shot.coursecal

import org.scalatest.FlatSpec


class ClusteredScheduleSpec  extends FlatSpec {



  "A Schedule" should  "correctly advance dates across segments" in {
    val topics = "src/test/resources/greek101.txt"
    val conf = "src/test/resources/greek101.yaml"
    val sched = Schedule(topics, conf)
    val segs = sched.segments

    for (i <- 0 until segs.size) {

      val s = segs(i)
      val w = s.weeks(0)

    }
  }

  it should "weave matched topics with calendar dates" in  {
    val topics = "src/test/resources/greek101.txt"
    val conf = "src/test/resources/greek101.yaml"
    val sched = Schedule(topics, conf)
    val semester = sched.datedTopics
    val expectedWeeks = 4
    assert(semester.size == expectedWeeks)
  }

  it should "format a segment of the topics list as a markdown table" in  {
    val topics = "src/test/resources/greek101.txt"
    val conf = "src/test/resources/greek101.yaml"
    val sched = Schedule(topics, conf)

    val md = sched.markdownCalendar
    val expectedLines = 25
    assert(md.split("\n").size == expectedLines)

  }


}
