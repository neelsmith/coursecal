package edu.holycross.shot.coursecal


/** Valid class schedule patterns at HC.*/
sealed trait SchedulePattern {
  /** Number of class meetings per week */
  def classes: Int
}

/** Tuesday-Thursday meeting pattern.*/
object TTh extends SchedulePattern { def classes = 2 }

/** Monday-Wednesday-Friday meeting pattern.*/
object MWF extends SchedulePattern {  def classes = 3}

/** Wednesday-Friday meeting pattern.*/
object WF extends SchedulePattern   { def classes = 2}
