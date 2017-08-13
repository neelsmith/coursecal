package edu.holycross.shot.coursecal


import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor
import java.time._
import java.time.format._

import scala.collection.JavaConversions.asScalaBuffer
import scala.collection.mutable.Buffer


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
* be included in the total.  (In your list [[CourseTopics]], you
* can indicate that no classes will meet at that point in the term.)
* @param fixedEvent Events outside the list of topics that have a
* fixed date assigned to them.
*/
case class CalendarConfig(title: String, weekOne: LocalDate, scheduleType: Schedule, calendarWeeks: Int, fixedEvents: Vector[FixedEvent] ) {

  /** Creates a semester calendar from the information in a
  * semester calendar configuration.
  *
  * @param conf Configuration of the calendar.
  * @return A semester calendar corresponding to the configuration.
  */


  /**
  */
  def getCalendarOption(): Option[Semester] = {

    scheduleType match {
      case MWF => Some(MonWedFriSemester(weekOne, calendarWeeks))
      case TTh => Some(TuesThursSemester(weekOne, calendarWeeks))
      case _ => None
    }

  }


  def calendar = {
    getCalendarOption().get
  }
}

object CalendarConfig {

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
