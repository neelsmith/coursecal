package edu.holycross.shot.coursecal


/** Valid class schedule patterns at HC.*/
sealed trait Schedule

/** Tuesday-Thursday meeting pattern.*/
object TTh extends Schedule

/** Monday-Wednesday-Friday meeting pattern.*/
object MWF extends Schedule

/** Wednesday-Friday meeting pattern.*/
object WF extends Schedule
