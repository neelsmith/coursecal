package io.github.neelsmith



package coursecal {
import scala.collection.mutable.ArrayBuffer

import java.time._
import java.time.temporal._
import java.util.Locale
import java.time.format._

class FixedEvent(val eventDate: LocalDate, val eventLabel: String)



object ScheduleMaker  {
  def schedule( topics: Syllabus, conf: CalendarConfig): String = {
    val yamlHeader = s"""---
layout: page
title: ${conf.pageTitle}
---

"""
    yamlHeader + interleaveWeeks(conf.calendar.weeks, topics.entries,0)
  }



  def gatherNotes(entries: Seq[SyllabusEntry]) = {
    val noteEntries = entries.filter {
      case e : CourseDay => e.notes.size > 0
      case e: SectionTopic => false
    }

    noteEntries.map { case e: CourseDay  => e.notes }.mkString(" ")
  }


  def formatTopic(topic: SyllabusEntry) = {
    topic match {

      case topic: CourseDay => topic.title // wrap in link, but only if not a no-assignment day
      case sect: SectionTopic  => "Oops.  Out of place  section topic: " + sect.title
    }
  }

  def formatWeek(wk: CourseWeek, entries: SyllabusEntry*) = {
    wk match {
    case wk: TuThWeek => {
      //val allEntries = entries
      // two entries in a tu-th week:
      "| " + shortDisplayDay(wk.tues) + ", " + shortDisplayDay(wk.thurs) + ". " + gatherNotes(entries) + " | " + formatTopic(entries(0)) + " | "  + formatTopic(entries(1)) + " |"
    }
    case wk: MonWedFriWeek => "MWF:  TBD"
    }
  }


  def tthTableHead() = {
    "\n| Week | Tues     |  Thurs     |\n| :------------- | :------------- |:------------- |\n"
  }
  def mwfTableHead() = {
    "\n| Week | Mon     | Topic     | Wed     | Topic     | Fri     | Topic     |Notes |\n| :------------- | :------------- |:------------- | :-------------| :-------------| :-------------|:-------------| :-------------|\n"
  }

  // weave Week data among sequence of topics
  def interleaveWeeks(weeksArray: ArrayBuffer[_ <: CourseWeek], topicsArray: ArrayBuffer[_ <: SyllabusEntry], count: Int): String = {

    if (topicsArray.isEmpty) ""

    else if (topicsArray.size == 1) {
      "\n\nDangling, single topic: " + topicsArray.head

    }  else {
      val currTopic = topicsArray.head
      currTopic match {
        case topic: SectionTopic => {
          weeksArray.head match {
            case tth: TuThWeek =>
            "##" + topic.title + "\n\n" + tthTableHead() + interleaveWeeks(weeksArray, topicsArray.tail, count)
            case mwf: MonWedFriWeek =>
            "##" + topic.title + "\n\n" + mwfTableHead() + interleaveWeeks(weeksArray, topicsArray.tail, count)
          }
        }
        case topic: CourseDay => {
          // num of elements to pop from topics depends on type of week
          val oneWeek = weeksArray.head
          oneWeek match {
            case tt: TuThWeek => {
              val tuesTopic = topicsArray.head
              val thursTopic = topicsArray.tail.head
              "| " + (count + 1).toString + formatWeek(weeksArray.head,tuesTopic,thursTopic) + "\n" +
              interleaveWeeks(weeksArray.tail, topicsArray.tail.tail, count + 1)
            }

            case mwf: MonWedFriWeek => {
              val monTopic = topicsArray.head
              val wedTopic = topicsArray.tail.head
              val friTopic = topicsArray.tail.tail.head
              "| " + (count + 1).toString + formatWeek(weeksArray.head,monTopic,wedTopic,friTopic) + "\n" +
              interleaveWeeks(weeksArray.tail, topicsArray.tail.tail.tail, count + 1)
            }
          }

          //"| " + (count + 1).toString + formatWeek(topicsArray.head, weeksArray.head) + "\n" + interleaveWeeks(weeksArray.tail, topicsArray.tail, count + 1)
        }
      }
    }
  }
}

}
