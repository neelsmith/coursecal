package io.github.neelsmith


import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor
import java.time._
import java.time.format._



package coursecal {

class CalendarConfig(confFileName: String) {
  val fName = confFileName
  val source = scala.io.Source.fromFile(fName)
  val yamlText = try source.mkString finally source.close()
  val yaml = new Yaml()
  val c = yaml.load(yamlText).asInstanceOf[java.util.Map[String, Any]]

  val yr = c.get("yr").asInstanceOf[Int]
  val mo = c.get("mo").asInstanceOf[Int]
  val da = c.get("da").asInstanceOf[Int]

  // Values we're really interested in:
  //val firstDay = LocalDate.of(yr,mo,da)
  /* getFirstDay(): LocalDate = {
    LocalDate.of(yr,mo,da)
  }*/
  val firstDay = LocalDate.of(yr,mo,da)
  val totalWeeks = c.get("totalWeeks").asInstanceOf[Int]
  val sched = c.get("schedule").asInstanceOf[String]

}
}


/*


def showFirstDay(conf: CalendarConfig): LocalDate = {
  val firstDay = conf.getFirstDay()
  println("First day: " + firstDay)
  firstDay
}



*/
