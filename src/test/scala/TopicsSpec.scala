

package edu.holycross.shot.coursecal

import org.scalatest.FlatSpec
import java.time._
import java.time.format._

class TopicsSpec  extends FlatSpec {

  "The Topics object" should "create a Syllabus from a file" in {
    val f = "src/test/resources/greek101.txt"
    val topics = Topics(f)
    topics match {
      case t: Topics => assert(true)
      case _ => fail("Should have created a Topics object")
    }
  }

  "A Topics list" should "extract course days from the list" in {
    val f = "src/test/resources/greek101.txt"
    val topics = Topics(f)
    val expectedDays = 3
    assert(topics.days.size == expectedDays)
  }

  it should "compute the number of weeks required for a given number of meetings per week" in {
      val f = "src/test/resources/greek101.txt"
      val topics = Topics(f)
      assert (topics.weeks(3) == 1)
      assert (topics.weeks(2) == 2)

  }

}
