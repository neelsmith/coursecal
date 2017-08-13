package edu.holycross.shot.coursecal

import scala.collection.mutable.ArrayBuffer

import java.time._
import java.time.temporal._
import java.util.Locale
import java.time.format._

import scala.collection.JavaConversions.asScalaBuffer
import scala.collection.mutable.Buffer




case class Schedule(topics: Topics, conf: CalendarConfig)  {


  def datedTopics = {
    val segmented = topics.weeklySegmented(conf.scheduleType.classes)
    val semesterCal = conf.semesterCalendar
    //for (seg <- segmented) {
      addDatedTopics(segmented, 0, Vector.empty)
      //println("Get " + seg.size + " weeks...")
      //println("CAL " + conf.semesterCalendar(i) + " AND " + segmented(0))
      //println(s"Get ${seg.size} calendar weeks")
    //}
  }

  def addDatedTopics(weeks: Vector[Vector[Week]], startingIndex: Int, calendarVector: Vector[DatedWeek]) : Vector[DatedWeek] = {
    if (weeks.isEmpty) {
      calendarVector
    } else {
      val nextSection = weeks(0)
      println(s"SECTION: with ${nextSection.size} weeks")
      val dWeeks = for (i <- 0 until nextSection.size) yield {
        val datedWeek = DatedWeek(nextSection(i), conf.semesterCalendar.weeks(i + startingIndex))
        println(s"${i}. Dated week: " + datedWeek.dates)
        datedWeek
      }
      if (calendarVector.isEmpty) {
        addDatedTopics(weeks.drop(1), startingIndex + nextSection.size, dWeeks.toVector)
      } else {
        addDatedTopics(weeks.drop(1), startingIndex + nextSection.size, calendarVector ++ dWeeks.toVector)
      }
    }
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

}
