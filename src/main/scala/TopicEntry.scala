package edu.holycross.shot.coursecal



/** Item that can appear in a sequence of course topics.
*/
trait TopicEntry {
  def label: String
}


/** Labelled section heading.
*
* @param level Heading level.
* @param title Title of section.
*/
case class SectionTopic(level: Int, title: String) extends TopicEntry {

  /** Markdown string */
  override def toString() = {
    val hash = for (i <- 0 until level) yield "#"
    hash.mkString + " " + title
  }

  /** Simple, human-readable label. */
  def label: String = {
    title
  }

}

/** Factory object for creating [[SectionTopic]]s from text source.
*/
object SectionTopic {

  /** Create a SectionTopic object from one line of text.
  *
  * @param ln The line of text.
  */
  def apply(ln: String ): SectionTopic = {
    val hdrPattern = "^(#+)(.+)".r
    val hdrPattern(level, txt) = ln
    SectionTopic(level.size, txt.trim)
  }
}

/** Content entry for one class meeting.
*
* @param title Title for meeting.
* @param notes Notes
* @param tags Tags
*/
case class CourseDay(title: String, notes: String, tags: Vector[DayTag] ) extends TopicEntry {

  /** Useful string serialization for debugging. */
  override def toString() = {
    if (notes.size > 0 ) {
      "Daily topic: " + title + " (notes: " + notes + ")"
    } else {
    "Daily topic: " + title + " (no notes)"
    }
  }

  /** Simple, human-readable label. */
  def label : String = {
    CourseDay.removeTags(title)
  }
}


/** Factory object for creating [[CourseDay]]s from text source.
*/
object CourseDay {

  /** Create a CourseDay object from one line of delimited text.
  *
  * @param ln The line of text.
  */
  def apply(ln: String ): Option[CourseDay] = {
    val cols = ln.split("#")
    val tagVector = tagsInText(cols(0))
    cols.size match {
      //case 3 =>  Some(CourseDay(cols(0),cols(1),cols(2),Array("")))
      case 2 =>  Some(CourseDay(cols(0).trim,cols(1).trim,Vector.empty[DayTag]))
      case 1 => Some(CourseDay(cols(0).trim,"",Vector.empty[DayTag]))
      case _ => { println("Error for line '" + ln + "', found " + cols.size + " columns.")
        None
      }
    }
  }

  /** Map of tagging strings to [[DayTag]]s */
  val tagsMap: Map[String,DayTag] = Map(
    "@none" -> NoClass
  )

  /** Find any occurrences of recognized tags in text.
  *
  * @param s Text to examine.
  */
  def tagsInText(s: String): Vector[DayTag] = {
    val tags = for (t <- tagsMap.keySet) yield {
      if (s.contains(t)) {
        Some(tagsMap(t))
      } else {
        None
      }
    }
    tags.flatten.toVector
  }


  /** Recursively remove a defined tag from a string until all
  * defined tags have been removed.
  *
  * @param s String to remove tags from.
  * @param tagList List of tags to remove.
  */
  def removeNextTag(s: String, tagList: Vector[String]): String = {
    if (tagList.isEmpty) {
      s
    } else {
      val thinner = s.replaceAll(tagList.head, "")
      removeNextTag(thinner.trim, tagList.drop(1))
    }
  }

  /** Remove all defined tags from a string.
  *
  * @param s String to remove tags from.
  */
  def removeTags(s: String): String = {
    removeNextTag(s.trim, tagsMap.keySet.toVector)
  }
}
