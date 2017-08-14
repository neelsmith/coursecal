

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
    val semester = sched.datedTopics(0)
    val expectedWeeks = 4
    assert(semester.size == expectedWeeks)
  }

  it should "generate an appropirate YAML header for a ghpages md file" in {
    val topics = "src/test/resources/greek101.txt"
    val conf = "src/test/resources/greek101.yaml"
    val sched = Schedule(topics, conf)

    val expected = "---\nlayout: page\ntitle: \"Greek 101, F'17: course schedule\"\n---\n\n"
    assert (sched.ghpageYamlHeader == expected)

  }


  it should "break up the schedule in to a series of segments" in {
    val topics = "src/test/resources/greek101.txt"
    val conf = "src/test/resources/greek101.yaml"
    val sched = Schedule(topics, conf)

    val segmentV = sched.segments
    val expectedSegments = 3
    assert(segmentV.size == expectedSegments)
  }


  it should "collect a Vector of FixedEvents for a given week" in {
    val topics = "src/test/resources/greek101.txt"
    val conf = "src/test/resources/greek101.yaml"
    val sched = Schedule(topics, conf)

    val referenceDate = LocalDate.parse("2017-08-30")
    val mwf = MonWedFriWeek(referenceDate)

    val fixed = sched.fixedEventsForWeek(mwf)
    assert(fixed.size == 1)

  }

  it should "correctly advance dates across segments" in pending
  // compare dates
  /*{
    val topics = "src/test/resources/greek101.txt"
    val conf = "src/test/resources/greek101.yaml"
    val sched = Schedule(topics, conf)
    val segs = sched.segments

    for (i <- 0 until segs.size) {

      val s = segs(i)
      val w = s.weeks(0)

    }
  }*/

  it should "format a segment of the topics list as a markdown table" in {
    val topics = "src/test/resources/greek101.txt"
    val conf = "src/test/resources/greek101.yaml"
    val sched = Schedule(topics, conf)

    val md = sched.markdownCalendar
    val expectedLines = 25
    assert(md.split("\n").size == expectedLines)
  }


}
