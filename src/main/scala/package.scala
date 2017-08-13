package edu.holycross.shot


import scala.io.Source
import scala.collection.mutable.ArrayBuffer
import java.time._
import java.time.temporal._
import java.util.Locale
import java.time.format._

/** Provides classes for working with academic course calendars.
 *
 *  ==Overview==
 *  The pattern for a semester-long course is configured by a
 *  [[CalendarConfig]] object, which can be instantiated from
 *  a yaml file.
 *
 *  Daily topics and assignments are managed by a [[Topics]]
 *  object, which can be instantiated from a simple text file
 *  listing topics in a sequence.
 *
 */
package object coursecal {


  def shortDisplayWeek(wk: CourseWeek) = {
    wk match {
      case wk: TuThWeek =>  "Tues., " + shortDisplayDay(wk.tues) + "\tThurs., " + shortDisplayDay(wk.thurs)
      case wk: MonWedFriWeek =>  "Mon., " + shortDisplayDay(wk.mon) + "\tWed., " + shortDisplayDay(wk.wed) + "\tFri., " + shortDisplayDay(wk.fri)
      case wk: WedFriWeek =>  "Wed., " + shortDisplayDay(wk.wed) + "\tFri., " + shortDisplayDay(wk.fri)
    }
  }

  // Format a readable short string to display a date
  def shortDisplayDay(d: LocalDate) =  {
    d.getMonth.getDisplayName(TextStyle.SHORT,Locale.US) + ". " + d.getDayOfMonth()
  }



}
