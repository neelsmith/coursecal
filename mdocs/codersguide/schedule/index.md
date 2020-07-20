---
title: "Schedule"
layout: page
parent: "Programmer's guide"
---


## The key class: `Schedule`

The `Schedule` class is where the magic happens: it brings together a collection of topics and a calendar configuration for a given semester-long course.



Build a `Schedule` from a pair of text files:

```scala mdoc
import edu.holycross.shot.coursecal._
val topics = "src/test/resources/latin101.txt"
val conf = "src/test/resources/latin101.yaml"
val sched = Schedule(topics, conf)
```

The parsed data sources are available from the new `Schedule`:

- the `topics` member is the result of parsing the topics file into a [topic group](../topicGroup/)
- the `conf` member is the result of parsing calendar file  into a [calendar configureation](../calendarConfig/)


```scala mdoc
sched.topics
sched.conf
```



## How it works




It can cluster daily topics together by labelled markdown headings in the topics file.  

The result is a  `Week` even though the resulting cluster may be multiple weeks.

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
