---
title: "DatedWeekMeetings"
layout: page
parent: "Programmer's guide"
---


The `DatedWeekMeetings` relates calendrical dates to course meeting patterns.  It is an abstract class extended by classes like `TThWeek`.

Given a calendar date, we can find the corresponding course week, and use it find dates for class meetings.

```scala
import java.time._
import java.time.format._
val startDate = LocalDate.parse("2017-08-30")
```

For example,

```scala
val tth = TuThWeek(startDate)
// tth: TuThWeek = TuThWeek(2017-08-30)
tth.tues
// res0: LocalDate = 2017-08-29
tth.thurs
// res1: LocalDate = 2017-08-31
```
