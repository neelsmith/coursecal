

package edu.holycross.shot.coursecal

import org.scalatest.FlatSpec


class SchedulePatternSpec  extends FlatSpec {


  "An instance of a SchedulePattern" should "indicate how many times a week the class meets" in {
    assert(MWF.classes == 3)
    assert(WF.classes == 2)
    assert(TTh.classes == 2)
  }


}
