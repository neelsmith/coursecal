

import java.net.URL
import scala.io.Source
import ammonite.ops._
import scala.collection.mutable.ArrayBuffer

abstract class SyllabusEntry
case class CourseDay(val title: String, val assignment: URL, val notes: String, val tags: Array[String] ) extends SyllabusEntry {}
case class SectionTopic(level: Int,title: String) extends SyllabusEntry {}


def courseDayForLine(ln: String ) = {
  CourseDay(ln,new URL("file:///dummy"),"",Array(""))
}


def resolveFileRef(fp: FilePath) = {
  fp match {
    case fp: RelPath => Path(fp, cwd)
    case fp: Path => fp
  }
}
class Syllabus (syllabusPath: FilePath ) {

  val syllabusFile = resolveFileRef(syllabusPath)
  val lns = read.lines!(syllabusFile)




  val hdrPattern = "^#+(.+)".r
  val emptyLine = "^$".r
  val nonEmpty = "([^#].+)".r
  var entryArray = new ArrayBuffer[SyllabusEntry]
  for (ln <- lns) {
    ln match  {
    case emptyLine(ln) =>
    case hdrPattern(ln) => {entryArray += SectionTopic(0,ln)}

    case  nonEmpty(ln) => {entryArray += courseDayForLine(ln)}

    case _ =>


    }
  }
}
/*
//Create a syllabus and traverse its entries:

val syllabus = new Syllabus(FilePath("testdata/syllabus1.txt"))
for (oneDay <- syllabus.entryArray) {
  println(oneDay)
}
*/
