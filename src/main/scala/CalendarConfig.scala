package io.github.neelsmith


import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor
import java.time._
import java.time.format._

import scala.collection.JavaConversions.asScalaBuffer
import scala.collection.mutable.Buffer

package coursecal {

class CalendarConfig(val confFileName: String) {

  val source = scala.io.Source.fromFile(confFileName)
  val yamlText = try source.mkString finally source.close()
  val yaml = new Yaml()
  val c = yaml.load(yamlText).asInstanceOf[java.util.Map[String, Any]]



  val pageTitle = c.get("pageTitle").asInstanceOf[String]
  val firstDay = LocalDate.parse(c.get("firstDay").asInstanceOf[String])
  val lastWeekIndex = c.get("totalWeeks").asInstanceOf[Int] - 1 // 0-origin
  val sched = c.get("schedule").asInstanceOf[String]


  val fixedBuffer : Buffer[String] = c.get("fixedDates").asInstanceOf[java.util.ArrayList[String]]
  val fixedEvents = fixedBuffer.map(s => s.split("==")).map{ a: Array[String] => a.map(_.trim) }.map { case a  => (new FixedEvent(LocalDate.parse(a(0)), a(1)))  }


  /** Creates a semester calendar from the information in a
  * semester calendar configuration.
  *
  * @param conf Configuration of the calendar.
  * @return A semester calendar corresponding to the configuration.
  */
  def getCalendarOption(): Option[Semester] = {
    sched.toLowerCase() match {
      case "mwf" => Some(MonWedFriSemester(firstDay, lastWeekIndex))
      //case "monwedfri" => MonWedFriSemester(conf.firstDay, conf.lastWeekIndex)
      case "tt" => Some(TuesThursSemester(firstDay, lastWeekIndex))
      //case "tth" => TuesThursSemester(conf.firstDay, conf.lastWeekIndex)
      //case "tuesthurs" => TuesThursSemester(conf.firstDay, conf.lastWeekIndex)
      case _ => None
    }
  }
  def calendar = {
    getCalendarOption().get
  }
}
}
