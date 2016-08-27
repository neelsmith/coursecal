
package io.github.neelsmith
package coursecal

import org.specs2.mutable._
import ammonite.ops._

class HelloCalUnitSpec extends Specification {
  "The CalendarConfig's file name" should {
   "contain 8 characters" in {
     val cal = new CalendarConfig("ica.yaml")
     cal.fName.pp must have size(8)

   }
 }

  "The resulting calendar" should {
    "have 16 entries" in {
      val conf = new CalendarConfig("ica.yaml")
      val cal = calendarForConfig(conf)
      cal.get.getWeeks() must have size(15)

    }
  }
  }
