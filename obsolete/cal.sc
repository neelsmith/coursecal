#!/usr/bin/env amm

/*
  Create a syllabus.

  Usage: "cal.sh SYLLABUSFILE CONFIGFILE"
*/

import ammonite.ops._

import $ivy.`default::coursecal:0.9.15`
import io.github.neelsmith.coursecal._

@main
def cal(  topicsFileName: String, confFileName: String) = {
  val syllabus = new Syllabus(topicsFileName)
  val conf = new CalendarConfig(confFileName)
  val sched = ScheduleMaker.schedule(syllabus,conf)

  println(sched)
}
