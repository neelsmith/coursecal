---
title: "Schedule"
layout: page
parent: "Programmer's guide"
nav_order: 0
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

The `TopicGroup` by itself can group topics into [`Week`s](./week/), cluster them by  labelled markdown headings.

The `datedTopics` method associates each week of topics with a calendrical week.

```scala mdoc
sched.datedTopics
```

A list of `DatedSegment`s

```scala mdoc
sched.segments
```

```scala mdoc
sched.segments.map(s => s.weeks)
```
