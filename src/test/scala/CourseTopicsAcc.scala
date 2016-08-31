
package io.github.neelsmith
package coursecal

import org.specs2._
import ammonite.ops._

class CourseTopicsAccSpec extends Specification {
  def is = s2"""

This is a specification to check CourseTopics.

The loaded test syllabus should contain 15 entries            $e1
The tested section topic labelled "Introduction" should be equal to
"Section: Introduction"                                       $e2
The toString function of tested course entry should yield
"Daily topic: Introduction to course"                         $e3
Collected notes from all entries should yield the String
"Note on week  Special activity this weekend"                                                      $e4
                                                                 """
  def  e1 = {
    val sy = new Syllabus("testdata/syllabus1.txt")
    sy.entries must have size(15)
  }
  def e2 = {
    SectionTopic(1,"Introduction").toString() should beEqualTo("Section: Introduction")
  }
  def e3 = {
    val ar = Array[String]()
    CourseDay("Introduction to course","",ar).toString() should beEqualTo("Daily topic: Introduction to course (no notes)")
  }


  def  e4 = {
    val sy = new Syllabus("testdata/syllabus1.txt")
    ScheduleMaker.gatherNotes(sy.entries).trim should beEqualTo("Note on week  Special activity this weekend")

  }

}
