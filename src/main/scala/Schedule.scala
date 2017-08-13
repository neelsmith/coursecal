package edu.holycross.shot.coursecal

import scala.collection.mutable.ArrayBuffer

import java.time._
import java.time.temporal._
import java.util.Locale
import java.time.format._

import scala.collection.JavaConversions.asScalaBuffer
import scala.collection.mutable.Buffer

case class Schedule(topics: Topics, conf: CalendarConfig)  {

}


/**
*/
object Schedule  {


  def apply(topicsFile: String, confFile: String): Schedule = {
    Schedule(Topics(topicsFile), CalendarConfig(confFile))
  }

/*
  def schedule( ): String = {
    val yamlHeader = s"""---
layout: page
title: ${conf.title}
---

"""
//    yamlHeader + interleaveWeeks(conf.calendar.weeks, conf.fixedEvents, topics.entries,0)
  yamlHeader
  }



  def gatherNotes(entries: Seq[TopicEntry]) = {
    val noteEntries = entries.filter {
      case e : CourseDay => e.notes.size > 0
      case e: SectionTopic => false
    }

    noteEntries.map { case e: CourseDay  => e.notes }.mkString(" ")
  }


  def formatTopic(topic: TopicEntry) = {
    topic match {

      case topic: CourseDay => topic.title // wrap in link, but only if not a no-assignment day
      case sect: SectionTopic  => "Oops.  Out of place  section topic: " + sect.title
    }
  }


// MODIFY HERE:
// PUT DATES FOR WEEK IN A COLLECTION
// AND THEN FILTER ALL FIXED DATES FOR PRESENCE OF VAL IN COLLECITON
// conf.fixedEvents.filter { _.eventDate == externalDate  }
  def formatWeek(
    fixedDates: Buffer[FixedEvent],
    wk: CourseWeek,
    entries: TopicEntry*
  ) = {
    wk match {
    case wk: TuThWeek => {
      val tuesSpecial = fixedDates.filter { _.eventDate == wk.tues}.map(_.eventLabel).mkString(" ")
      val thursSpecial = fixedDates.filter { _.eventDate == wk.thurs }.map(_.eventLabel).mkString(" ")
      val specials = tuesSpecial + thursSpecial

      //val allEntries = entries
      // two entries in a tu-th week:
      "| *" + shortDisplayDay(wk.tues) + ", " + shortDisplayDay(wk.thurs) + "*. " + specials + gatherNotes(entries) + " | " + formatTopic(entries(0)) + " | "  + formatTopic(entries(1)) + " |"
    }
    case wk: MonWedFriWeek => "MWF:  TBD"
    case wk: WedFriWeek => "WF: TBD"
    }
  }
*/

  def tthTableHead() = {
    "\n| Week | Notes | Tues     |  Thurs     |\n| :------------- |:------------- | :------------- |:------------- |\n"
  }
  def mwfTableHead() = {
    "\n| Week | Mon     | Topic     | Wed     | Topic     | Fri     | Topic     |Notes |\n| :------------- | :------------- |:------------- | :-------------| :-------------| :-------------|:-------------| :-------------|\n"
  }

  // weave Week data among sequence of topics
  def interleaveWeeks(
    weeksArray: ArrayBuffer[_ <: CourseWeek],
    fixedDates :    Buffer[FixedEvent],
    topicsArray: ArrayBuffer[_ <: TopicEntry],
    count: Int): String = {
      ""

/*
    if (topicsArray.isEmpty) ""

    else if (topicsArray.size == 1) {
      "\n\nDangling, single topic: " + topicsArray.head

    }  else {
      val currTopic = topicsArray.head
      currTopic match {
        case topic: SectionTopic => {
          weeksArray.head match {
            case tth: TuThWeek =>
            "\n##" + topic.title + "\n\n" + tthTableHead() + interleaveWeeks(weeksArray, fixedDates,topicsArray.tail, count)
            case mwf: MonWedFriWeek =>
            "\n##" + topic.title + "\n\n" + mwfTableHead() + interleaveWeeks(weeksArray,fixedDates, topicsArray.tail, count)
          }
        }
        case topic: CourseDay => {
          // num of elements to pop from topics depends on type of week
          val oneWeek = weeksArray.head
          oneWeek match {
            case tt: TuThWeek => {
              val tuesTopic = topicsArray.head
              val thursTopic = topicsArray.tail.head

              "| " + (count + 1).toString + formatWeek(fixedDates, weeksArray.head,tuesTopic,thursTopic) + "\n" +
              interleaveWeeks(weeksArray.tail, fixedDates, topicsArray.tail.tail, count + 1)
            }

            case mwf: MonWedFriWeek => {
              val monTopic = topicsArray.head
              val wedTopic = topicsArray.tail.head
              val friTopic = topicsArray.tail.tail.head
              "| " + (count + 1).toString + formatWeek(fixedDates, weeksArray.head,monTopic,wedTopic,friTopic) + "\n" +
              interleaveWeeks(weeksArray.tail, fixedDates, topicsArray.tail.tail.tail, count + 1)
            }
            case wk: WedFriWeek => "WF: TBD"
          }
        }
      }
    }*/
  }
}
