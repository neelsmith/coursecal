---
title: "Schedule"
layout: page
---

```scala mdoc:invisible
import edu.holycross.shot.coursecal._

```

The `Schedule` class is where the magic happens: it brings together a [collection of topics](../topics/) and a [configuration](../calendarConfig/) for a given semester-long course.

Build it from text files:



```scala mdoc:invisible
val topics = "src/test/resources/greek101.txt"
val conf = "src/test/resources/greek101.yaml"
val sched = Schedule(topics, conf)
```

Some stuff it has:
