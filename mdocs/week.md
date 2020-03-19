---
title: "Week"
layout: page
---

```scala mdoc:invisible
import edu.holycross.shot.coursecal._

import java.time._
import java.time.format._
```



Given a date, we can find the corresponding course week:

```scala mdoc:silent
val startDate = LocalDate.parse("2017-08-30")
val tth = TuThWeek(startDate)
```
