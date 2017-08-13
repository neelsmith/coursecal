

package edu.holycross.shot.coursecal

import org.scalatest.FlatSpec


class SectionTopicSpec  extends FlatSpec {


  "The SectionTopic object" should "build a section topic from text" in {
    val topic = SectionTopic("## Part 2")
    assert(topic.level ==2)
    assert(topic.title == "Part 2")
  }


}
