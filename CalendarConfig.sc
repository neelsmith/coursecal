
import $ivy.`org.yaml:snakeyaml:1.16`

import ammonite.ops._
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor

import java.time._
import java.time.format._
import $file.SemesterCal, SemesterCal._

//def calendarFor(classSched: String, dayOne: LocalDate, weekCount: Int) = {
def calendarForConfig(conf: CalendarConf) = {
  conf.sched.asInstanceOf[String].toLowerCase() match {
    case "mwf" => MonWedFri(conf.firstDay, conf.totalWeeks)
    case "tth" => TuesThurs(conf.firstDay, conf.totalWeeks)
  }
}

class CalendarConf(confFileName: String) {
  val yamlText = read! cwd/confFileName
  val yaml = new Yaml()
  val c = yaml.load(yamlText).asInstanceOf[java.util.Map[String, Any]]
  val yr = c.get("yr").asInstanceOf[Int]
  val mo = c.get("mo").asInstanceOf[Int]
  val da = c.get("da").asInstanceOf[Int]


  val firstDay = LocalDate.of(yr,mo,da)
  val totalWeeks = c.get("totalWeeks").asInstanceOf[Int]
  val sched = c.get("schedule")

}
