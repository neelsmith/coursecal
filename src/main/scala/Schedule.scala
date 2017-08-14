package edu.holycross.shot.coursecal

import scala.collection.mutable.ArrayBuffer

import java.time._
import java.time.temporal._
import java.util.Locale
import java.time.format._

import scala.collection.JavaConversions.asScalaBuffer
import scala.collection.mutable.Buffer



/** A class for working with lists of topics and calendrical
* data to create schedules for a course.
*
* @param topics List of daily topics.
* @param conf Configuration of calendrical data.
*/
case class Schedule(topics: Topics, conf: CalendarConfig)  {

  /** Compose a markdown string representing lines in
  * a calendar table for a single segment of the course topics.
  */
  def markdownSegment(v: Vector[Week]): String  = {
    println(v)

    ""
  }

  def markdownCalendar: String = {
    //ghpageYamlHeader
    //datedTopics

    ""
  }




  /** Create a course-long sequence of [[DatedWeek]]s.
  * Topics are grouped into header-delimited segments,
  * and clustered into week units of the size configured
  * in `conf`.
  */
  def datedTopics: Vector[DatedWeek] = {
    addDatedTopics(weeklySegmented, 0, Vector.empty)
  }


  /** Create a course-long sequence of [[DatedWeek]]s by recursively
  *
  */
  def addDatedTopics(
    weeks: Vector[Vector[Week]],
    startingIndex: Int,
    calendarVector: Vector[DatedWeek]) : Vector[DatedWeek] = {

    if (weeks.isEmpty) {
      calendarVector

    } else {
      val nextSection = weeks(0)
      val dWeeks = for (i <- 0 until nextSection.size) yield {
        val datedWeek = DatedWeek(nextSection(i), conf.semesterCalendar.weeks(i + startingIndex))
        datedWeek
      }
      if (calendarVector.isEmpty) {
        addDatedTopics(weeks.drop(1), startingIndex + nextSection.size, dWeeks.toVector)
      } else {
        addDatedTopics(weeks.drop(1), startingIndex + nextSection.size, calendarVector ++ dWeeks.toVector)
      }
    }
  }


  /** Cluster topics into a series of [[Week]]s by segments
  * labelled with headings.
  */
  def weeklySegmented: Vector[Vector[Week]] = {
    topics.weeklySegmented(conf.scheduleType.classes)
  }

  /** Compute number of calendar weeks configured in
  * topics list for configured schedule of class meetings.
  */
  def topicsWeeks : Int = {
    topics.weeks(conf.scheduleType.classes)
  }

  /** True if number of weeks of scheduled topics
  * equals number of configured weeks in calendar.
  */
  def isComplete: Boolean = {
    (topicsWeeks == conf.calendarWeeks)
  }



  /** YAML header for markdown source for ghpage
  * version of calendar. */
  val ghpageYamlHeader = s"""---
layout: page
title: "${conf.title}"
---
"""

}


/**
*/
object Schedule  {

  /** Create a Schedule from pair of files.
  *
  * @param topicsFile Text file with topics for the semester.
  * @param confFile YAML file configuring calendar for the semester.
  */
  def apply(topicsFile: String, confFile: String): Schedule = {
    Schedule(Topics(topicsFile), CalendarConfig(confFile))
  }


  def tthTableHead: String = {
    "\n| Week | Notes | Tues     |  Thurs     |\n| :------------- |:------------- | :------------- |:------------- |\n"
  }
  def mwfTableHead: String = {
    "\n| Week | Mon     | Topic     | Wed     | Topic     | Fri     | Topic     |Notes |\n| :------------- | :------------- |:------------- | :-------------| :-------------| :-------------|:-------------| :-------------|\n"
  }

}
