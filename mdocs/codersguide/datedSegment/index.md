---
title: "Segment"
layout: page
parent: "Programmer's guide"
---

```scala mdoc:invisible
import edu.holycross.shot.coursecal._

import java.time._
import java.time.format._
```


```scala mdoc:invisible
val topics = "src/test/resources/latin101.txt"
val conf = "src/test/resources/latin101.yaml"
val schedule = Schedule(topics, conf)
```

The `DatedSegment` class is a labelled part of a course calendar.  You'll see this in the `segments` member of a [schedule](../schedule/).

```scala mdoc
// How many labelled segments are in this schedule?
schedule.segments.size
```

Each `DatedSegment` has a `weeks` member, which is a Vector of [dated course weeks](../datedWeek/).


```scala mdoc
val firstSegment = schedule.segments.head
// How many weeks are in the first segment?
firstSegment.weeks.size
```
