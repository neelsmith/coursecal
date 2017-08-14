

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
    seg1(0) match {
      case  st: SectionTopic => {
        assert(st.level == 2)
        val expectedTitle = "Section 1: introduction"
        assert(st.title == expectedTitle)
      }
      case _ => fail("Should have found a SectionTopic")
    }
  }

  "A Topics list" should "extract course days from the list" in {
    val f = "src/test/resources/greek101.txt"
    val topics = Topics(f)
    val expectedDays = 12
    assert(topics.days.size == expectedDays)
  }

  it should "compute the number of weeks required for a given number of meetings per week" in {
      val f = "src/test/resources/greek101.txt"
      val topics = Topics(f)
      assert (topics.weeks(3) == 4)
      assert (topics.weeks(2) == 6)
  }

  it should "provide a mechanism for clustering topics in sublists grouped under headings" in {
    val f = "src/test/resources/greek101.txt"
    val topics = Topics(f)
    val targetEntries : Vector[TopicEntry]= Vector.empty[TopicEntry]

    val topicsV : Vector[Topics] = Vector.empty
    val clusters = topics.addSegment(topics.entries, topicsV)

    val expectedClusters = 3
    assert(clusters.size == expectedClusters)
  }

  it should "cluster topics in sublists by heading from a parameterless function" in {
    val f = "src/test/resources/greek101.txt"
    val topics = Topics(f)
    val cluster = topics.segments
    val expectedClusters = 3
    assert(cluster.size == expectedClusters)
  }


  it should "cluster classes into weeks for a given number of meetings per week" in {
      val f = "src/test/resources/greek101.txt"
      val topics = Topics(f)
      val byWeek = topics.weekly(3)
      val expectedWeeks = 4
      assert(byWeek.size == expectedWeeks)
  }

  it should "cluster classes into weeks within a clustering by topics identified by heading" in {
      val f = "src/test/resources/greek101.txt"
      val topics = Topics(f)
      val segmented = topics.weeklySegmented(3)
      val expectedSegs = 3
      assert(segmented.size == expectedSegs)
  }

  it should "extract headings when they exist" in {
    val f = "src/test/resources/greek101.txt"
    val topics = Topics(f)
    assert(topics.heading.get == SectionTopic(2, "Section 1: introduction"))
  }
}
