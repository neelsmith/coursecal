package edu.holycross.shot.coursecal

import scala.io.Source
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap


/** Convenience class clustering a sequence of [[TopicEntry]]s
* for a single week.
*
* @param entries Topics for a single week.
* @param dimension Number of class meetings in this week.
*/
case class Week(entries : Vector[TopicEntry], dimension: Int)
