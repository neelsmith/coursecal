

package edu.holycross.shot.coursecal

import org.scalatest.FlatSpec
import java.time._
import java.time.format._

class SegmentSpec  extends FlatSpec {


  val topics = Topics("src/test/resources/greek101.txt")
  val conf = CalendarConfig("src/test/resources/greek101.yaml")
  val segmentTopics = Topics.nextSegment(topics.entries, Vector.empty[TopicEntry])


  "A Schedule" should "be able to construct a segment from a topics list" in {
    val schedule = Schedule(topics,conf)
    val segment = schedule.segment(segmentTopics, 0)

    segment match {
      case s: Segment => {
        val hdg = s.heading.get
        val expectedLevel = 2
        val expectedTitle = "Section 1: introduction"
        assert (hdg.level == expectedLevel)
        assert(hdg.title == expectedTitle)
        val expectedWeeks = 1
        assert(s.weeks.size == expectedWeeks)
      }
      case _ => fail("Should have created segment")
    }
  }

  "A Segment" should "compose a markdown representation of the segment for a tabular calendar" in {
    val schedule = Schedule(topics,conf)
    val segment = schedule.segment(segmentTopics, 0)
    val mdText = segment.markdown(Schedule.mwfTableHead)
    val lines = mdText.split("\n")
    assert(lines.size == 5)
  }
}
