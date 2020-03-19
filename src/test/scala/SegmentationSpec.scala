

package edu.holycross.shot.coursecal

import org.scalatest.FlatSpec
import java.time._
import java.time.format._

class SegmentationSpec  extends FlatSpec {


  "A Schedule" should "correctly advance dates across segments" in  {
    val topics = "src/test/resources/tiniest.txt"
    val conf = "src/test/resources/greek101.yaml"
    val sched = Schedule(topics, conf)

    val segs = sched.segments
  /*
    for (i <- 0 until segs.size)  {

      val s = segs(i)
      val w = s.weeks.head
      println(s"SEGMENT ${i}, FIRST WEEK OF SEG STARTS: " + s.weeks.head.dates.dates.head)

    }

    val segStarts = segs.map(s => s.weeks.head.dates.dates.head)
    //println(sched.markdownCalendar)
    assert(segStarts.toSet.size == segs.size)*/
  }

}
