

package edu.holycross.shot.coursecal

import org.scalatest.FlatSpec
import java.time._
import java.time.format._

class DatedSegmentSpec  extends FlatSpec {


  val topics = TopicGroup("src/test/resources/greek101.txt")
  val conf = CalendarConfig("src/test/resources/greek101.yaml")

  "The TopicGroup object" should "segment topics into dated groups" in {
    //val segmentTopicGroup = TopicGroup.nextCluster(topics.entries, Vector.empty[TopicEntry])
  }




  "A Schedule" should "be able to construct a segment from a topics list" in  pending /*{
    val schedule = Schedule(topics,conf)
    val segment = schedule.segment(segmentTopicGroup, 0)
    println(segment)

    segment match {
      case s: DatedSegment => {


        val hdg = s.heading.get
        val expectedLevel = 2
        val expectedTitle = "Section 1: introduction"
        assert (hdg.level == expectedLevel)
        assert(hdg.title == expectedTitle)
            /*
        val expectedWeeks = 1
        assert(s.weeks.size == expectedWeeks)
        */
      }
      case _ => fail("Should have created segment")
    }
  } */


  "A DatedSegment" should "compose a markdown representation of the segment for a tabular calendar" in pending /* {
    val schedule = Schedule(topics,conf)
    val segment = schedule.segment(segmentTopicGroup, 0)
    val mdText = segment.markdown(Schedule.mwfTableHead)
    val lines = mdText.split("\n")
    assert(lines.size == 5)
  }*/

  it should "do more" in pending
}
