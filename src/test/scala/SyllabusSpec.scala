

package edu.holycross.shot.coursecal

import org.scalatest.FlatSpec
import java.time._
import java.time.format._

class TopicsSpec  extends FlatSpec {

  "The Topics object" should "create a Syllabus from a file" in {
    val f = "src/test/resources/greek101.txt"
    val syllabus = Topics(f)
    syllabus match {
      case s: Topics => assert(true)
      case _ => fail("Should have created a Syllabus")
    }
  }

}
