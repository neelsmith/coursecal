---
title: "User's guide"
layout: page
has_children: true
nav_order: 1
---


You can manage your course schedule using two files:

- a [calendar configuration file](calendarFile/), with information specific to a particular semester.  This file is in `yaml` format, and configures a schedule for a particular semester, with information like what the course meeting pattern is (e.g., MWF?  TR?), when the term begins, and how many weeks it runs.
- a [topics file](topics/).  This is a simple text file with one line in markdown format for each class meeting.  The topics file has no information specific to the calendar or schedule of a particular semester, so you can easily reuse it or modify for future offerings of the same course.




Here are some old notes from the project wiki that should be updated and properly run here:

Initial notes on how to use this.

What you need:

- a `.yaml` file configuring your course for a particular semester
- a text file with a sequence of topics

## Building a calendar page

Create a `Schedule` from the topics file and `.yaml` file

```scala mdoc:invisible
import edu.holycross.shot.coursecal._

val conf = "src/test/resources/coins.yaml"
val topics = "src/test/resources/coins.txt"

val sched = Schedule(topics, conf)
```
The schedule object can create a markdown page:

```scala mdoc:invisible
val md = sched.markdownCalendar
import java.io.PrintWriter
new PrintWriter("schedule.md"){write(md);close;}
```


## Structure of `.yaml` configuration
