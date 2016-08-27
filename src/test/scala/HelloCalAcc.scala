
package io.github.neelsmith
package coursecal

import org.specs2._


class HelloCalAccSpec extends Specification {
  def is = s2"""

 This is a specification to check constructing a CalendarConfig.

 The name of the config file should contain 8 characters    $e1
 The text of the config file should begin,
 "# Configure a course calendar"                            $e2
 The short text for the first day should begin "Aug."       $e3
                                                                 """

  def e1 = { val cal = new CalendarConfig("ica.yaml")
   cal.fName  must have size(8)}
  def e2 = { val cal = new CalendarConfig("ica.yaml")
    cal.yamlText  must startWith("# Configure a course calendar")}
  def e3 = { val cal = new CalendarConfig("ica.yaml")
    shortDisplayDay(cal.firstDay) must beEqualTo("Aug. 25")}
}
