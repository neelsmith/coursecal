

package edu.holycross.shot.coursecal

import org.scalatest.FlatSpec
import java.time._
import java.time.format._

class SegmentedIndexSpec  extends FlatSpec {

  "The Schedule object" should "break up the schedule in to a series of segments" in {
    val topics = "src/test/resources/greek101.txt"
    val conf = "src/test/resources/greek101.yaml"
    val sched = Schedule(topics, conf)

    val segmentV = sched.segments
    val expectedSegments = 3
    assert(segmentV.size == expectedSegments)
  }


  /*
  it should  "compute the number of weeks in the topics list" in {
    val topics = "src/test/resources/greek101.txt"
    val conf = "src/test/resources/greek101.yaml"
    val sched = Schedule(topics, conf)
    val expectedWeeks = 4
    assert(sched.topicsWeeks == expectedWeeks)
  }




  it should "correctly advance dates across segments" in {
    val topics = "src/test/resources/greek101.txt"
    val conf = "src/test/resources/greek101.yaml"
    val sched = Schedule(topics, conf)
    val segs = sched.segments
    println("SEGS: " + segs.size)
    for (i <- 0 until segs.size) {
      println("SEG " + i)
      val s = segs(i)
      val w = s.weeks(0)
      println("\thas " + w.dates)
    }
  }*/



}
