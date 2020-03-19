---
title: "CourseWeek"
layout: page
---

```scala mdoc:invisible
import edu.holycross.shot.coursecal._

```

The `CourseWeek` relates calendrical dates to course meeting patterns.  It is an abstract class extended by classes like `TThWeek`.

Given a calendar date, we can find the corresponding course week, and use it find dates for class meetings.

```scala mdoc:silent
import java.time._
import java.time.format._
val startDate = LocalDate.parse("2017-08-30")
```

For example,

```scala mdoc
val tth = TuThWeek(startDate)
tth.tues
tth.thurs
```
