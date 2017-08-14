

package edu.holycross.shot.coursecal

import org.scalatest.FlatSpec
import java.time._
import java.time.format._

class SegmentSpec  extends FlatSpec {

  val f = "src/test/resources/greek101.txt"
  val topics = Topics(f)
  val segmentTopics = Topics.nextSegment(topics.entries, Vector.empty[TopicEntry])

  "The Segment object" should "construct a segment from a topics list plus some confiugration info" in {
    val weekSize = 3
    val segment = Segment(segmentTopics, weekSize, 0)
    println("TOPICS: " + segmentTopics)
    segment match {
      case s: Segment => {
        //println(s.heading.get)
      }
      case _ => fail("Should have created segment")
    }
  }

}
