---
title: "User's guide"
layout: page
has_children: true
nav_order: 1
---


## User's guide


### Data for scheduling


You can manage your course schedule using two files:

- a [calendar configuration file](./calendarFile/), with information specific to a particular semester.  This file is in `yaml` format, and configures a schedule for a particular semester, with information like what the course meeting pattern is (e.g., MWF?  TR?), when the term begins, and how many weeks it runs.
- a [topics file](./topics/).  This is a text file with one line in markdown format for each class meeting.  The topics file has no information specific to the calendar or schedule of a particular semester, so you can easily reuse it or modify for future offerings of the same course.


## Building a calendar

### Using `sbt`

```scala
scalaVersion := "2.12.10"
resolvers += Resolver.jcenterRepo
resolvers += Resolver.bintrayRepo("neelsmith","maven")
libraryDependencies ++=   Seq(
 "edu.holycross.shot"  %% "coursecal" % "2.0.0"
)
```


### Using ammonite shell

>ADD EQUIVALENT AMMONITE IMPORT

### Generic scala

```scala mdoc
import edu.holycross.shot.coursecal._
import java.io.PrintWriter

def schedule = {
  val pg = "ghpages/schedule/index.md"
  val sched = Schedule("topics.txt", "calendar.yaml")
  val md = sched.markdownCalendar
  new PrintWriter(pg) { write(md); close }
  println("Schedule written to " + pg)
}


println("\nWrite a new schedule to ghpages directory:")
println("\tschedule")
```
