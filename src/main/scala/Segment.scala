package edu.holycross.shot.coursecal

import scala.io.Source
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap

case class Segment(weeks : Vector[Week], initialIndex: Int, heading: Option[SectionTopic])

object Segment {

  def apply( entries : Vector[TopicEntry], weekSize: Int, startingIndex: Int = 0): Segment = {
    entries(0) match {
      case day : CourseDay => {
        val weeks = Topics(entries).weekly(weekSize)
        Segment(weeks, startingIndex, None)
      }
      case topic : SectionTopic => {
        val weeks = Topics(entries.drop(1)).weekly(weekSize)
        val heading = Some(topic)
        Segment(weeks, startingIndex, heading)
      }
    }
  }
}
