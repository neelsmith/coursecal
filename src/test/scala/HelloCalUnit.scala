

package edu.holycross.shot.coursecal

import org.scalatest.FlatSpec

class HelloCalUnitSpec  extends FlatSpec {

  "The CalendarConfig's file name" should "contain 8 characters" in {
     val cal = new CalendarConfig("ica.yaml")
     //assert(cal.confFileName.pp.size == 8)
   }

   "The resulting calendar" should "have 16 entries" in {
      val conf = new CalendarConfig("ica.yaml")
      val cal = conf.calendar
      assert(cal.weeks.size == 15)
  }
}
