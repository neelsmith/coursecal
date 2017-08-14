
import $file.CourseWeek, CourseWeek._
import $file.CourseTopics, CourseTopics._
import $file.SemesterCal, SemesterCal._
import scala.collection.mutable.ArrayBuffer



def formatWeek(entry: SyllabusEntry, wk: CourseWeek) = {
shortDisplayWeek(wk)
}


def zipWeeks(weeksArray: ArrayBuffer[_ <: CourseWeek], topicsArray: ArrayBuffer[_ <: SyllabusEntry], count: Int): String = {

  if (weeksArray.isEmpty) ""
  else {
    val currTopic = topicsArray.head
    currTopic match {
      case topic: SectionTopic => topic.title + "\n" + zipWeeks(weeksArray, topicsArray.tail, count)
      case topic: CourseDay => count.toString + ": " + formatWeek(topicsArray.head, weeksArray.head) + "\n" + zipWeeks(weeksArray.tail, topicsArray.tail, count + 1)
    }


  }

  //else { count.toString + ": " + shortDisplayWeek(weeksArray.head) + "\n" + zipWeeks(weeksArray.tail, topicsArray, count + 1) }
}
