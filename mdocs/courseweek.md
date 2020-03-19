---
title: "CourseWeek"
layout: page
---

```scala mdoc:invisible
import edu.holycross.shot.coursecal._

import java.time._
import java.time.format._
```

The `CourseWeek` relates calendrical dates to course meeting patterns.  It is an abstract class extended by classes like `TThWeek`.

Given a calendar date, we can find the corresponding course week, and use it find dates for class meetings.

```scala mdoc:silent
val startDate = LocalDate.parse("2017-08-30")
val tth = TuThWeek(startDate)
```
For example,
```scala mdoc
tth.tues
tth.thurs
```
