package edu.holycross.shot.coursecal


/** Association of one week of daily topics with a week of
* calendrical data for a given class meeting pattern.
*
* @param topics A [[Week]] of course topics.
* @param dates One week of calendar dates for a course meeting pattern.
*/
case class DatedWeek(topics: Week, dates: CourseWeek, fixedEvents: Vector[FixedEvent] = Vector.empty[FixedEvent]) {


  /** Collect a Vector of notes for all [[CourseDay]]s in this week.*/
  def notes: Vector[String] = topics.courseDays.map(_.notes).filter(_.nonEmpty)

  /** Fold notes for this week into a single String. */
  def notesString: String = notes.mkString(" ")

  def fixedLabels: Vector[String] = fixedEvents.map(_.eventLabel)

  def fixedString: String = fixedLabels.mkString(" ")


  /** Format a string representing a week on a course calendar
  * as one line of a markdown table.
  */
  def calendarString: String = {
    dates match {
      case tth : TuThWeek => tthString
      case mwf : MonWedFriWeek => mwfString
      case wf : WedFriWeek => wfString
    }
  }

  /** Format a string representing a week on a Tues-Thurs course calendar
  * as one line of a markdown table.
  */
  def tthString: String = {
    dates match {
      case tth : TuThWeek => s"|${shortDisplayDay(tth.tues)}-${shortDisplayDay(tth.thurs)} | ${topics.entries(0).label} | ${topics.entries(1).label} | ${fixedString} ${notesString} |\n"

      case mwf : MonWedFriWeek => ""
      case wf : WedFriWeek => ""
    }
  }

  /** Format a string representing a week on a Mon-Wed-Fri course calendar
  * as one line of a markdown table.
  */
  def mwfString: String = {
    dates match {
      case tth : TuThWeek => ""
      case wf : WedFriWeek => ""

      case mwf : MonWedFriWeek => {

        s"|${shortDisplayDay(mwf.mon)}-${shortDisplayDay(mwf.fri)} | ${topics.entries(0).label} | ${topics.entries(1).label}| ${topics.entries(2).label} |   ${fixedString}  ${notesString} |\n"
      }

    }
  }

  /** Format a string representing a week on a Wed-Fri course calendar
  * as one line of a markdown table.
  */
  def wfString: String = {
    dates match {
      case tth : TuThWeek =>  ""
      case mwf : MonWedFriWeek => ""
      case wf : WedFriWeek => s"|${shortDisplayDay(wf.wed)}-${shortDisplayDay(wf.fri)} | ${topics.entries(0).label} | ${topics.entries(1).label}| ${topics.entries(2).label} |   ${fixedString}  ${notesString} |\n"

    }
  }


}
