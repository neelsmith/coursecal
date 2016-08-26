
import $ivy.`org.yaml:snakeyaml:1.16`

import ammonite.ops._
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor

import java.time._
import java.time.format._
import $file.SemesterCal, SemesterCal._
import $file.CourseWeek, CourseWeek._



def calendarForConfig(conf: CalendarConf) = {
  conf.sched.toLowerCase() match {
    case "mwf" => MonWedFri(conf.getFirstDay(), conf.totalWeeks)
    case "monwedfri" => MonWedFri(conf.getFirstDay(), conf.totalWeeks)
    case "tt" => TuesThurs(conf.getFirstDay(), conf.totalWeeks)
    case "tth" => TuesThurs(conf.getFirstDay(), conf.totalWeeks)
    case "tuesthurs" => TuesThurs(conf.getFirstDay(), conf.totalWeeks)
    //case _ =>
  }
}

def showFirstDay(conf: CalendarConf): LocalDate = {
  val firstDay = conf.getFirstDay()
  println("First day: " + firstDay)
  firstDay
}


class CalendarConf(confFileName: String) {
  val yamlText = read! cwd/confFileName
  val yaml = new Yaml()
  val c = yaml.load(yamlText).asInstanceOf[java.util.Map[String, Any]]

  val yr = c.get("yr").asInstanceOf[Int]
  val mo = c.get("mo").asInstanceOf[Int]
  val da = c.get("da").asInstanceOf[Int]

  // Values we're really interested in:
  //val firstDay = LocalDate.of(yr,mo,da)
  def getFirstDay(): LocalDate = {
    LocalDate.of(yr,mo,da)
  }
  val totalWeeks = c.get("totalWeeks").asInstanceOf[Int]
  val sched = c.get("schedule").asInstanceOf[String]
}
