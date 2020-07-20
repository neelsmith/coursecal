

package edu.holycross.shot.coursecal

import org.scalatest.FlatSpec
import java.time._
import java.time.format._

class LatinTopicsSpec  extends FlatSpec {

  "The TopicGroup object" should "create a TopicGroupList from a file" in {
    val f = "src/test/resources/latin101.txt"
    val topics = TopicGroup(f)
    topics match {
      case t: TopicGroup => assert(true)
      case _ => fail("Should have created a TopicGroup object")
    }
  }


  it should "extract a heading-delimited segment from a list"  in {
    val f = "src/test/resources/latin101.txt"
    val topics = TopicGroup(f)

    val seg1 = TopicGroup.nextCluster(topics.entries, Vector.empty[TopicEntry])
    val expectedSize = 7
    assert( seg1.size == expectedSize)
    seg1(0) match {
      case  st: SectionTopic => {
        assert(st.level == 2)
        val expectedTitle = "Latin nouns and adjectives"
        assert(st.title == expectedTitle)
      }
      case _ => fail("Should have found a SectionTopic")
    }
  }

  "A TopicGroup list" should "extract course days from the list" in {
    val f = "src/test/resources/latin101.txt"
    val topics = TopicGroup(f)
    val expectedDays = 42
    assert(topics.days.size == expectedDays)
  }

  it should "compute the number of weeks required for a given number of meetings per week" in {
      val f = "src/test/resources/latin101.txt"
      val topics = TopicGroup(f)
      assert (topics.weeks(3) == 14)
      assert (topics.weeks(2) == 21)
  }


  it should "extract headings when they exist" in {
    val f = "src/test/resources/latin101.txt"
    val topics = TopicGroup(f)
    assert(topics.heading.get == SectionTopic(2, "Latin nouns and adjectives"))
  }
}
