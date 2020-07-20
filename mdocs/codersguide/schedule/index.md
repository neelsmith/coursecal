---
title: "Schedule"
layout: page
parent: "Programmer's guide"
---

```scala mdoc:invisible
import edu.holycross.shot.coursecal._

```

The `Schedule` class is where the magic happens: it brings together a [collection of topics](../topics/) and a [configuration](../calendarConfig/) for a given semester-long course.

Build a `Schedule` from a pair of text files:

```scala mdoc:invisible
val topics = "src/test/resources/latin101.txt"
val conf = "src/test/resources/latin101.yaml"
val sched = Schedule(topics, conf)
```

## How it works


Entries in the topics file are parsed into a [topic group](../topicGroup/).


It can cluster daily topics together by  labelled markdown headings in the topics file.  The result is unfortunately called a `Week` even though the resulting cluster may be multiple weeks.

> This needs to be changed in an API-breaking update.

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
