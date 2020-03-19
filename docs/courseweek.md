---
title: "CourseWeek"
layout: page
---


The `CourseWeek` relates calendrical dates to course meeting patterns.  It is an abstract class extended by classes like `TThWeek`.

Given a calendar date, we can find the corresponding course week, and use it find dates for class meetings.

```scala
val startDate = LocalDate.parse("2017-08-30")
val tth = TuThWeek(startDate)
```
For example,
```scala
tth.tues
// res0: LocalDate = 2017-08-29
tth.thurs
// res1: LocalDate = 2017-08-31
```
