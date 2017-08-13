
package edu.holycross.shot.coursecal


import org.scalatest.FlatSpec


class HelloCalAccSpec extends FlatSpec {
  /*
  def is = s2"""

 This is a specification to check constructing a CalendarConfig.

 The name of the config file should contain 8 characters    $e1
 The text of the config file should begin,
 "# Configure a course calendar"                            $e2
 The short text for the first day should begin "Aug."       $e3
                                                                 """

  def e1 = { val cal = new CalendarConfig("ica.yaml")
   cal.confFileName  must have size(8)}
  def e2 = { val cal = new CalendarConfig("ica.yaml")
    cal.yamlText  must startWith("# Configure a course calendar")}
  def e3 = { val cal = new CalendarConfig("ica.yaml")
    shortDisplayDay(cal.firstDay) must beEqualTo("Aug. 25")}
    */
}
