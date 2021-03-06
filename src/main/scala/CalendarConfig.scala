package edu.holycross.shot.coursecal


import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor
import java.time._
import java.time.format._

import scala.collection.JavaConversions.asScalaBuffer
import scala.collection.mutable.Buffer

/*
import wvlet.log._
import wvlet.log.LogFormatter.SourceCodeLogFormatter
*/

/** Configuration data needed to compute a course schedule
* for a given course in a given semester.
*
* @param title Title of course calendar.
* @param dayOne A date falling anywhere in the first week
* of classes.
* @param scheduleType Pattern of weekly class meetings.
* @param calendarWeeks Number of weeks to schedule.  Note
* that these are real calendar weeks:  if there are no classes
* for an entire week (e.g., spring break), that week should still
* be included in the total.  (In your list of course topics, you
* can indicate that no classes will meet at that point in the term.)
* @param fixedEvent Events outside the list of topics that have a
* fixed date assigned to them.
*/
case class CalendarConfig(
  title: String,
  weekOne: LocalDate,
  scheduleType: SchedulePattern,
  calendarWeeks: Int,
  fixedEvents: Vector[FixedEvent] )  {



    // THIS IS FOOBAR
  /** Create a new [[CalendarConfig]] identical to this one,
  * but with a different starting date.
  *
  * @param startDate A calendar date in the first week of
  * the new calendar.
  */
  def resetWeekOne(startDate: LocalDate): CalendarConfig  = {
    val newCal = CalendarConfig(title, startDate, scheduleType, calendarWeeks, fixedEvents)
    //debug("Reset calendar using weekOne " + startDate)
    newCal
  }

  /**
  *
  * @param weekIndex Zero-origin index into week of this calendar.
  * New [[CalendarConfig]] will begin from this week.
  */
  def dateForWeek(weekIndex: Int): LocalDate = {
    calForWeek(weekIndex).dates.head
  }

  /** Create a new [[CalendarConfig]] identical to this one,
  * but with a different starting date.
  *
  * @param weekIndex Zero-origin index into week of this calendar.
  * New [[CalendarConfig]] will begin from this week.
  */
  def resetWeekOne(weekIndex: Int): CalendarConfig = {
    val newStartDate = dateForWeek(weekIndex)
    //debug("From index " + weekIndex + ", resetting cal to " + newStartDate)
    CalendarConfig(title, newStartDate, scheduleType, calendarWeeks, fixedEvents)
  }

  /** Find a semester calendar for the configured dates
  * and type.
  */
  def calendar: Option[Semester] = {
    scheduleType match {
      case MWF => Some(MonWedFriSemester(weekOne, calendarWeeks))
      case TTh => Some(TuesThursSemester(weekOne, calendarWeeks))
      case WF => Some(WedFriSemester(weekOne, calendarWeeks))
      case _ => None
    }
  }

  def semesterCalendar: Semester = {
    scheduleType match {
      case MWF => MonWedFriSemester(weekOne, calendarWeeks)
      case TTh => TuesThursSemester(weekOne, calendarWeeks)
      case WF =>  WedFriSemester(weekOne, calendarWeeks)
    }
  }


  /** Compute week calendar for a given week of the semester
  * identified by 1-origin index.
  *
  * @param weekNumber Week index.
  */
  def calForWeek(weekNumber: Int):  DatedWeekMeetings = {
    val referenceDate = weekOne.plusWeeks(weekNumber)

    val newCal = scheduleType match {
      case MWF => MonWedFriWeek(referenceDate)
      case TTh => TuThWeek(referenceDate)
      case WF =>  WedFriWeek(referenceDate)
    }


    newCal
  }
}


/** Factory object for making [[CalendarConfig]]s
* from a YAML source file.
*/
object CalendarConfig {

  /** Create a [[CalendarConfig]] from a configuration file.
  *
  * @param confFileName YAML file with configuration data.
  */
  def apply(confFileName: String): CalendarConfig = {
    val source = scala.io.Source.fromFile(confFileName)
    val yamlText = try source.mkString finally source.close()
    val yaml = new Yaml()
    val c = yaml.load(yamlText).asInstanceOf[java.util.Map[String, Any]]

    val pageTitle = c.get("pageTitle").asInstanceOf[String]
    val firstDay = LocalDate.parse(c.get("firstDay").asInstanceOf[String])

    val totalWeeks = c.get("totalWeeks").asInstanceOf[Int]

    val schedString = c.get("schedule").asInstanceOf[String]
    val scheduleType = schedString.toLowerCase match {
      case "mwf" => MWF
      case "tt" => TTh
      case "wf" => WF
    }

    val fixedBuffer : Buffer[String] = c.get("fixedDates").asInstanceOf[java.util.ArrayList[String]]
    val fixedVector = fixedBuffer.toVector
    val pairs = fixedVector.map(s => s.split("=="))
    val trimmed = pairs.map{ a: Array[String] => a.map(_.trim) }
    val fixedEvents =  trimmed.map { case a  => (new FixedEvent(LocalDate.parse(a(0)), a(1)))  }


    CalendarConfig(pageTitle, firstDay, scheduleType,totalWeeks, fixedEvents)
  }
}
