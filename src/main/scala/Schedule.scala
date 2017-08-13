package edu.holycross.shot.coursecal

import scala.collection.mutable.ArrayBuffer

import java.time._
import java.time.temporal._
import java.util.Locale
import java.time.format._

import scala.collection.JavaConversions.asScalaBuffer
import scala.collection.mutable.Buffer

case class Schedule(topics: Topics, conf: CalendarConfig)  {


  def topicsOnCalendar = {
    for (seg <- topics.weeklySegmented(conf.scheduleType.classes)) {

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
