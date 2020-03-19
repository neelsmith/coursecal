

package edu.holycross.shot.coursecal

import org.scalatest.FlatSpec
import java.time._
import java.time.format._

class TopicGroupSpec  extends FlatSpec {

  "The TopicGroup object" should "create a TopicGroupList from a file" in {
    val f = "src/test/resources/greek101.txt"
    val topics = TopicGroup(f)
    topics match {
      case t: TopicGroup => assert(true)
      case _ => fail("Should have created a TopicGroup object")
    }
  }


  it should "extract a heading-delimited segment from a list"  in {
    val f = "src/test/resources/greek101.txt"
    val topics = TopicGroup(f)

    val seg1 = TopicGroup.nextCluster(topics.entries, Vector.empty[TopicEntry])
    val expectedSize = 4
    assert( seg1.size == expectedSize)
    seg1(0) match {
      case  st: SectionTopic => {
        assert(st.level == 2)
        val expectedTitle = "Section 1: introduction"
        assert(st.title == expectedTitle)
      }
      case _ => fail("Should have found a SectionTopic")
    }
  }

  "A TopicGroup list" should "extract course days from the list" in {
    val f = "src/test/resources/greek101.txt"
    val topics = TopicGroup(f)
    val expectedDays = 12
    assert(topics.days.size == expectedDays)
  }

  it should "compute the number of weeks required for a given number of meetings per week" in {
      val f = "src/test/resources/greek101.txt"
      val topics = TopicGroup(f)
      assert (topics.weeks(3) == 4)
      assert (topics.weeks(2) == 6)
  }


  it should "extract headings when they exist" in {
    val f = "src/test/resources/greek101.txt"
    val topics = TopicGroup(f)
    assert(topics.heading.get == SectionTopic(2, "Section 1: introduction"))
  }
}
