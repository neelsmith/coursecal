

package edu.holycross.shot.coursecal

import org.scalatest.FlatSpec
import java.time._
import java.time.format._

class TopicsSpec  extends FlatSpec {

  "The Topics object" should "create a TopicsList from a file" in {
    val f = "src/test/resources/greek101.txt"
    val topics = Topics(f)
    topics match {
      case t: Topics => assert(true)
      case _ => fail("Should have created a Topics object")
    }
  }


  it should "extract a heading-delimited segment from a list"  in {
    val f = "src/test/resources/greek101.txt"
    val topics = Topics(f)

    val seg1 = Topics.nextSegment(topics.entries, Vector.empty[TopicEntry])
    val expectedSize = 4
    assert( seg1.size == expectedSize)

  }
/*
  "A Topics list" should "extract course days from the list" in {
    val f = "src/test/resources/greek101.txt"
    val topics = Topics(f)
    val expectedDays = 9
    assert(topics.days.size == expectedDays)
  }

  it should "compute the number of weeks required for a given number of meetings per week" in {
      val f = "src/test/resources/greek101.txt"
      val topics = Topics(f)
      assert (topics.weeks(3) == 3)
      assert (topics.weeks(2) ==5)
  }

  it should "cluster classes into weeks for a given number of meetings per week" in {
      val f = "src/test/resources/greek101.txt"
      val topics = Topics(f)
      val x = topics.weekly(2)
  }
*/
}
