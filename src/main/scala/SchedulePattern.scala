package edu.holycross.shot.coursecal


/** Valid class schedule patterns at HC.*/
sealed trait SchedulePattern

/** Tuesday-Thursday meeting pattern.*/
object TTh extends SchedulePattern

/** Monday-Wednesday-Friday meeting pattern.*/
object MWF extends SchedulePattern

/** Wednesday-Friday meeting pattern.*/
object WF extends SchedulePattern
