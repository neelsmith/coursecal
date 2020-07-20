---
title: "Schedule"
layout: page
parent: "Programmer's guide"
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

## Some stuff it has

It can cluster topics into a series of [[Week]]s by segments labelled with headings

```scala mdoc
sched.weeklyClustered
```

A list of `DatedSegment`s

```scala mdoc
sched.segments
```

```scala mdoc
sched.segments.map(s => s.weeks)
```
