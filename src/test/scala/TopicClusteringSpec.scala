package edu.holycross.shot.coursecal

import org.scalatest.FlatSpec
import java.time._
import java.time.format._

class TopicClusteringSpec  extends FlatSpec {

  val f = "src/test/resources/tiniest.txt"
  val topics = TopicGroup(f)

  "The TopicGroup object" should "cluster topics in sublists by heading from a parameterless function" in {
    val cluster = topics.clusters
    val expectedClusters = 2
    assert(cluster.size == expectedClusters)
  }


  it should "cluster classes into weeks for a given number of meetings per week" in {
      val f = "src/test/resources/greek101.txt"
      val topics = TopicGroup(f)
      val byWeek = topics.weekly(3)
      val expectedWeeks = 4
      assert(byWeek.size == expectedWeeks)
  }

  it should "cluster classes into weeks within a clustering by topics identified by heading" in  {
      val f = "src/test/resources/greek101.txt"
      val topics = TopicGroup(f)
      val segmented = topics.weeklyClustered(3)
      val expectedSegs = 3
      assert(segmented.size == expectedSegs)
  }

  it should "extract headings when they exist" in {
    val f = "src/test/resources/greek101.txt"
    val topics = TopicGroup(f)
    assert(topics.heading.get == SectionTopic(2, "Section 1: introduction"))
  }
}
