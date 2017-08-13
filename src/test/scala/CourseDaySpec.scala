

package edu.holycross.shot.coursecal

import org.scalatest.FlatSpec


class CourseDaySpec  extends FlatSpec {


  "The CourseDay object" should "build a course day from delimited text" in {
    val courseDay = CourseDay("[Class 1](assignment1) # Note on week")
    courseDay match {
      case cd: CourseDay => assert(true)
      case _ => fail("Should have created a CourseDay")
    }
  }




}
