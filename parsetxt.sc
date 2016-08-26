/*
  Create a syllabus and traverse its entries:
*/

import ammonite.ops._

import $file.CourseTopics, CourseTopics._

@main
def main(s: String) = {
  val syllabus = new Syllabus(FilePath(s))
  for (oneDay <- syllabus.entryArray) {
    println(oneDay)
  }
}
