package edu.holycross.shot.coursecal


import java.time._
import java.time.temporal._
import java.util.Locale
import java.time.format._

import wvlet.log._
import wvlet.log.LogFormatter.SourceCodeLogFormatter


/** A class for working with lists of topics and calendrical
* data to create schedules for a course.
*
* @param topics List of daily topics.
* @param conf Configuration of calendrical data.
*/
case class Schedule(topics: TopicGroup, conf: CalendarConfig) extends LogSupport {

  ////Logger.setDefaultLogLevel(LogLevel.DEBUG)
  debug("Created Schedule instance.")
  /** Find fixed events for a given week.
  *
  * @param courseWeek Week to check.
  */
  def fixedEventsForWeek(courseWeek: DatedWeekMeetings): Vector[FixedEvent] = {
    conf.fixedEvents.filter(_.inWeek(courseWeek))
  }

  /**  Compose a markdown page including ghpages YAML
  * header that formats an entire term as a calendar table,
  * or tables.
  */
  def markdownCalendar: String = {
    debug("Make markdown string for calendar")
    conf.scheduleType match {
        case MWF =>  {
          val segs = segments.map(_.markdown(Schedule.mwfTableHead))
          ghpageYamlHeader + segs.mkString("\n\n")
        }
        case TTh => {
          debug("Determine segments for a TTh course schedule")
          val segs = segments.map(_.markdown(Schedule.tthTableHead))
          ghpageYamlHeader + segs.mkString("\n\n")
        }
        case WF => {
          val segs = segments.map(_.markdown(Schedule.wfTableHead))
          ghpageYamlHeader + segs.mkString("\n\n")
        }
    }
  }


  /** Create a course-long sequence of [[DatedWeek]]s.
  * TopicGroup are grouped into header-delimited segments,
  * and clustered into week units of the size configured
  * in `conf`.
  */



  /** Create a course-long sequence of [[DatedWeek]]s by recursively
  * converting a Vector of [[Week]]s into a single Vector of [[DatedWeek]].
  *
  * @param weeks  Sections of the course clustered as a Vector of [[Week]]s,
  * with each Vector corresponding to a segment of the course.
  * @param calendarVector The previously accumulated list of [[DatedWeek]]s.
  * @param startWeek Week count for first week of this section of topics.
  *
  */

  /*(
,
  calendarVector: Vector[DatedWeek],
  startWeek: Int
) */


  def datedTopics : Vector[DatedWeek] = {
    val calendarWeeks = conf.semesterCalendar.weeks.size
    val weeks = topics.weekly(conf.scheduleType.classes)

    //Logger.setDefaultLogLevel(LogLevel.DEBUG)
    debug("adding dated topics")//: weeks = " + weeks.flatten.size)
    debug("organized in " + weeks.size + " segments of " + conf.scheduleType.classes)
    debug("total weeks in semester: " + calendarWeeks)


    val dWeeks = for ( (wk,i) <- weeks.zipWithIndex) yield {
        val calendarWeek = conf.semesterCalendar.weeks(i)
        val fixed = fixedEventsForWeek(calendarWeek)
        val datedWeek = DatedWeek(wk, calendarWeek, fixed)
        debug(calendarWeek + " = wk: " + wk + " and dixed evts " + fixed)
        debug("YIELDS " + datedWeek)
        datedWeek
    }

    //Logger.setDefaultLogLevel(LogLevel.INFO)

    dWeeks

/*


      val dWeeks = for (i <- startWeek until limit) yield {

        val calendarWeek = conf.semesterCalendar.weeks(i)
        val fixed = fixedEventsForWeek(calendarWeek)

        val datedWeek = DatedWeek(nextSection(i), calendarWeek, fixed)
        datedWeek
      }

      val newStart = startWeek + dWeeks.size
      //Logger.setDefaultLogLevel(LogLevel.DEBUG)
      debug("NEW WEEK INDEX STARTS AT " + newStart + " after adding " + dWeeks.size + " new dated weeks.")
      //Logger.setDefaultLogLevel(LogLevel.INFO)

      if (calendarVector.isEmpty) {
        //Logger.setDefaultLogLevel(LogLevel.DEBUG)
        debug("Adding first section with week count " + newStart)
        //Logger.setDefaultLogLevel(LogLevel.INFO)
        addDatedTopics(weeks.tail, dWeeks.toVector, newStart)
      } else {
        addDatedTopics(weeks.tail, calendarVector ++ dWeeks.toVector, newStart)
      }*/


  }


  /** Cluster topics into a series of [[Week]]s by segments
  * labelled with headings.
  */
  def weeklyClustered: Vector[Vector[Week]] = {
    topics.weeklyClustered(conf.scheduleType.classes)
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

  /** Break schedule up into segments.
  */
  def segments : Vector[DatedSegment] = {
    val clusters = topics.clusters
    val v = Vector.empty[DatedSegment]
    addSegment(clusters, v, 0)
  }

  /** Recursively add content from a source Vector of [[Topic]]s to create a
  * Vector of [[DatedSegment]]s.
  *
  * @param src Vector of [[Topic]]s to be processed.
  * @param target Vector of previously collected [[DatedSegment]]s.
  * @param weekCount Week number within course as a whole.
  */
  def addSegment(
    src: Vector[TopicGroup],
    target: Vector[DatedSegment],
    weekCount: Int = 0
  ) : Vector[DatedSegment] = {

    if (src.isEmpty) {
      target
    } else {
      debug("Number of topic segments: " + src.size)
      debug("Week count: " + weekCount)
      debug("Classes per week: "+ conf.scheduleType.classes)
      debug("Weeks: " + src(0).weeks(conf.scheduleType.classes))
      val newWeekCount = weekCount + src.head.weeks(conf.scheduleType.classes)

      val seg = segment(src.head.entries, weekCount)
      if (target.size == 0) {
        addSegment(src.tail, Vector(seg), newWeekCount)
      } else{
        val newTarget = target ++ Vector(seg)
        addSegment(src.tail, newTarget, newWeekCount)
      }
    }
  }


  /** Create a [[DatedSegment]] object from a series of [[TopicEntry]]s a
  * 0-origin index into the list of weeks.
  *
  * @param entries Optional [[SectionTopic]] followed by a series of [[CourseDay]]s.
  * @param weekCount 0-origin index into the list of weeks.
  */
  def segment(
    entries : Vector[TopicEntry], weekCount: Int): DatedSegment = {
    //Logger.setDefaultLogLevel(LogLevel.DEBUG)
    // FIRST FIND A DATE FOR START.
    val newConf= conf.resetWeekOne(weekCount)
    // SET UP A CONFIG FOR THAT.
    debug("Segmenting " + entries.size + " TopicEntrys at week count " + weekCount )

    debug("->" + entries.mkString("\n->"))
    //Logger.setDefaultLogLevel(LogLevel.INFO)
    entries.head match {
      case day : CourseDay => {
        debug("First entry is a CourseDay")

        Logger.setDefaultLogLevel(LogLevel.DEBUG)
        debug("Creating mini schedule for segment of " + entries.size + " entries")
        Logger.setDefaultLogLevel(LogLevel.INFO)

        val datedWeeks = Schedule(TopicGroup(entries), newConf).datedTopics
        DatedSegment(datedWeeks, weekCount, None)
      }
      case topic : SectionTopic => {

        //Logger.setDefaultLogLevel(LogLevel.DEBUG)
        debug("First entry is a SectionTopic: from tail of entries, create new Schedule to get dated topics ")

        val entriesSchedule = Schedule(TopicGroup(entries.tail), newConf)
        //Logger.setDefaultLogLevel(LogLevel.DEBUG)
        debug("Created schedule for remaining entries with " + entriesSchedule.topics.size + " topics.")
        //Logger.setDefaultLogLevel(LogLevel.INFO)
        val datedWeeks = entriesSchedule.datedTopics
        val heading = Some(topic)
        debug("Heading is " + heading)
        //Logger.setDefaultLogLevel(LogLevel.INFO)
        DatedSegment(datedWeeks, weekCount, heading)
      }
    }
  }

  /** YAML header for markdown source for ghpage
  * version of calendar. */
  val ghpageYamlHeader = s"""---
layout: page
title: "${conf.title}"
---

"""

}


/** Factory for creating [[Schedule]]s, and definition of static
* header strings for markdown tables.
*/
object Schedule  extends LogSupport {

  /** Create a Schedule from pair of files.
  *
  * @param topicsFile Text file with topics for the semester.
  * @param confFile YAML file configuring calendar for the semester.
  */
  def apply(topicsFile: String, confFile: String): Schedule = {
    debug("Creating schedule from files " + topicsFile + " and " + confFile)
    Schedule(TopicGroup(topicsFile), CalendarConfig(confFile))
  }


  /** Markdown heading for a calendar table on a Tues-Thurs schedule.
  */
  def tthTableHead: String = {
    "\n| Week | Tues | Thurs     |     Notes  |\n| :------------- |:------------- | :------------- |:------------- |\n"
  }

  /** Markdown heading for a calendar table on a Mon-Wed-Fri schedule.
  */
  def mwfTableHead: String = {
    "\n| Week | Mon     |  Wed     |  Fri     | Notes |\n| :------------- | :------------- |:------------- | :-------------| :-------------|\n"
  }

  /** Markdown heading for a calendar table on a Wed-Fri schedule.
  */
  def wfTableHead: String = {
    "\n| Week | Wed      | Fri     |Notes |\n|  :-------------| :-------------|:-------------| :-------------|\n"
  }

}
