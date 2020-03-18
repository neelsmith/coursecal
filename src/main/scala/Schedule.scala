package edu.holycross.shot.coursecal

import scala.collection.mutable.ArrayBuffer

import java.time._
import java.time.temporal._
import java.util.Locale
import java.time.format._

import scala.collection.JavaConversions.asScalaBuffer
import scala.collection.mutable.Buffer

import wvlet.log._
import wvlet.log.LogFormatter.SourceCodeLogFormatter


/** A class for working with lists of topics and calendrical
* data to create schedules for a course.
*
* @param topics List of daily topics.
* @param conf Configuration of calendrical data.
*/
case class Schedule(topics: Topics, conf: CalendarConfig) extends LogSupport {

  //Logger.setDefaultLogLevel(LogLevel.DEBUG)
  debug("Created Schedule instance.")
  /** Find fixed events for a given week.
  *
  * @param courseWeek Week to check.
  */
  def fixedEventsForWeek(courseWeek: CourseWeek): Vector[FixedEvent] = {
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
  * Topics are grouped into header-delimited segments,
  * and clustered into week units of the size configured
  * in `conf`.
  */
  def datedTopics(startingIndex: Int = 0): Vector[DatedWeek] = {
    addDatedTopics(weeklySegmented, startingIndex, Vector.empty)
  }


  /** Create a course-long sequence of [[DatedWeek]]s by recursively
  * converting a Vector of [[Week]]s into a single Vectorof [[DatedWeek]].
  *
  * @param weeks  Sections of the course clustered as a Vector of [[Week]]s,
  * with each Vector corresponding to a segment of the course.
  * @param startingIndex Index of the first week of this segment within
  * the course as a whole.
  * @param calendarVector The resulted list of [[DatedWeek]]s.
  *
  */
  def addDatedTopics(
    weeks: Vector[Vector[Week]],
    startingIndex: Int,
    calendarVector: Vector[DatedWeek]) : Vector[DatedWeek] = {

    if (weeks.isEmpty) {
      calendarVector

    } else {
      debug("adding dated topics: weeks = " + weeks.flatten.size)
      val calendarWeeks = conf.semesterCalendar.weeks.size
      debug("compare remaining calendar weeks: " + calendarWeeks)
      val nextSection = weeks.head

      val limit = if (calendarWeeks > nextSection.size) {
        nextSection.size
      } else {
        calendarWeeks
      }
      debug("NEXT SECTION: \n" + weeks.flatten.mkString("\n"))

      // SO HERE IT IS.
      // CRASH IF MORE CONTENT THAN WEEKS LEFT IN COURSE!



      //val dWeeks = for (i <- 0 until nextSection.size) yield {
      val dWeeks = for (i <- 0 until limit) yield {
        debug("Getting dated week using index " + i + " and starting point " + startingIndex)
        val calendarWeek = conf.semesterCalendar.weeks(i + startingIndex)
        val fixed = fixedEventsForWeek(calendarWeek)

        val datedWeek = DatedWeek(nextSection(i), calendarWeek, fixed)
        datedWeek
      }

      val newIdx = startingIndex + dWeeks.size
      if (calendarVector.isEmpty) {
        addDatedTopics(weeks.tail, newIdx, dWeeks.toVector)
      } else {
        addDatedTopics(weeks.tail, newIdx, calendarVector ++ dWeeks.toVector)
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

  /** Break schedule up into segments.
  */
  def segments : Vector[Segment] = {
    val segs = topics.segments
    val v = Vector.empty[Segment]
    addSegment(segs, v, 0)
  }

  /** Recursively add content from a source Vector of [[Topic]]s to create a
  * Vector of [[Segment]]s.
  *
  * @param src Vector of [[Topic]]s to be processed.
  * @param target Vector of previously collected [[Segment]]s.
  * @param idx Some damned index or other.
  */
  def addSegment(src: Vector[Topics], target: Vector[Segment], idx: Int = 0) : Vector[Segment] = {
    if (src.isEmpty) {
      target
    } else {
      debug("Number of topics: " + src.size)
      debug("Prev idx: " + idx)
      debug("Dimension: "+ conf.scheduleType.classes)
      debug("Weeks: " + src(0).weeks(conf.scheduleType.classes))
      val newIdx = idx + src(0).weeks(conf.scheduleType.classes)

      // this is wrong...
      val seg = segment(src(0).entries, idx)
      if (target.size == 0) {
        addSegment(src.drop(1), Vector(seg), newIdx)
      } else{
        val newTarget = target ++ Vector(seg)
        addSegment(src.drop(1), newTarget, newIdx)
      }
    }
  }


  /** Create a [[Segment]] object from a series of [[TopicEntry]]s and some
  * configuration data.
  *
  * @param entries Optional [[SectionTopic]] followed by a series of [[CourseDay]]s.
  * @param weekSize Number of class meetings per week.
  * @param startingIndex Index of first week of this segment within the whole term.
  */
  def segment(
    entries : Vector[TopicEntry],
    startingIndex: Int): Segment = {

    debug("Segmenting " + entries.size + " TopicEntrys, counting from " + startingIndex )
    entries.head match {
      case day : CourseDay => {
        //val weeks = Topics(entries).weekly(conf.scheduleType.classes)
        debug("First entry is a CourseDay: create new schedule to get dated topics from index "+ startingIndex)
        val datedWeeks = Schedule(Topics(entries), conf).datedTopics(startingIndex)
        Segment(datedWeeks, startingIndex, None)
      }
      case topic : SectionTopic => {
        //val weeks = Topics(entries.drop(1)).weekly(conf.scheduleType.classes)
        debug("First entry is a SectionTopic: from tail of entries, create new Schedule to get dated topics from index " + startingIndex)
        val entriesSchedule = Schedule(Topics(entries.tail), conf)
        debug("Created schedule for remianing entries")
        val datedWeeks = entriesSchedule.datedTopics(startingIndex)
        val heading = Some(topic)
        debug("Heading is " + heading)
        Segment(datedWeeks, startingIndex, heading)
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
    Schedule(Topics(topicsFile), CalendarConfig(confFile))
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
