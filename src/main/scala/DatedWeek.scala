package edu.holycross.shot.coursecal

case class DatedWeek(topics: Week, dates: CourseWeek) {

  def calendarString: String = {
    dates match {
      case tth : TuThWeek => tthString
      case mwf : MonWedFriWeek => mwfString
      case wf : WedFriWeek => wfString
    }
  }

  def tthString: String = {
    dates match {
      case tth : TuThWeek => s"${shortDisplayDay(tth.tues)}-${shortDisplayDay(tth.thurs)} | ${topics.entries(0)} | ${topics.entries(1)}"
      case mwf : MonWedFriWeek => ""
      case wf : WedFriWeek => ""
    }
  }
  def mwfString: String = {
    dates match {
      case tth : TuThWeek => ""
      case mwf : MonWedFriWeek => s"${shortDisplayDay(mwf.mon)}-${shortDisplayDay(mwf.fri)} | ${topics.entries(0)} | ${topics.entries(1)}| ${topics.entries(2)} |"
      case wf : WedFriWeek => ""
    }
  }
  def wfString: String = {
    dates match {
      case tth : TuThWeek =>  ""
      case mwf : MonWedFriWeek => ""
      case wf : WedFriWeek => shortDisplayDay(wf.wed)
    }
  }


}
