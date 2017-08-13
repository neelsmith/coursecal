package edu.holycross.shot


/** Provides classes for working with academic course calendars.
 *
 *  ==Overview==
 *  The pattern for a semester-long is configured by a
 *  [[CalendarConfig]] object, which can be instantiated from
 *  a yaml file.
 *
 *  Daily topics and assignments are managed by a [[CourseTopics]]
 *  object, which can be instantiated from a simple file in
 *  delimited-text format.
 *
 *  
 */
package coursecal {

  /** Valid class schedule patterns at HC.*/
  sealed trait Schedule
  object TTh extends Schedule
  object MWF extends Schedule
  object WF extends Schedule
}
