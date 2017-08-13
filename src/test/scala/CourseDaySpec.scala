

package edu.holycross.shot.coursecal

import org.scalatest.FlatSpec


class CourseDaySpec  extends FlatSpec {


  "The CourseDay object" should "build a course day from delimited text" in {
    val courseDay = CourseDay("[Class 1](assignment1) # Note on week")
    courseDay match {
      case cd: Some[CourseDay] => {
        val day = cd.get
        assert(day.title == "[Class 1](assignment1)")
        assert(day.notes == "Note on week")
        assert(day.tags.isEmpty)
      }
      case  None => fail("Should have created a CourseDay option")
    }
  }

  it should "trim whitespace in notes and text" in {
      val courseDay = CourseDay("  [Class 1](assignment1) #   Note on week  ")
      courseDay match {
        case cd: Some[CourseDay] => {
          val day = cd.get
          assert(day.title == "[Class 1](assignment1)")
          assert(day.notes == "Note on week")
          assert(day.tags.isEmpty)
        }
        case  None => fail("Should have created a CourseDay option")
      }
  }


  it should "accept empty weekly notes" in {
      val courseDay = CourseDay("[Class 1](assignment1)")
      courseDay match {
        case cd: Some[CourseDay] => {
          val day = cd.get
          assert(day.title == "[Class 1](assignment1)")
          assert(day.notes == "")
          assert(day.tags.isEmpty)
        }
        case  None => fail("Should have created a CourseDay option")
      }
  }

  it should "identify defined tags occuring in a string" in {
    val tags = CourseDay.tagsInText("Advising @none")
    val expected = Vector(NoClass)
    assert (tags == expected)
  }

  it should "strip recognized tags from a string" in {
    val detagged = CourseDay.removeTags("Advising @none")
    assert(detagged == "Advising")
  }

}
