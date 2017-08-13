

package edu.holycross.shot.coursecal

import org.scalatest.FlatSpec
import java.time._
import java.time.format._

class SyllabusSpec  extends FlatSpec {

  "The Syllabus object" should "create a Syllabus from a file" in {
    val f = "src/test/resources/greek101.txt"
    val syllabus = Syllabus(f)
    syllabus match {
      case s: Syllabus => assert(true)
      case _ => fail("Should have created a Syllabus")
    }
  }

}
