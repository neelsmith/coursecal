---
title: "TopicGroup"
layout: page
parent: "Programmer's guide"
nav_order: 1
---

```scala mdoc:invisible
import edu.holycross.shot.coursecal._

import java.time._
import java.time.format._
```

We can load topics from a text file. Each non-empty line is either an entry for a class day, or a header for a section of the course.  The difference between the two is that headers start with one or more `#` characters indiciating the header level in markdown output.

```scala mdoc:silent
val f = "src/test/resources/greek101.txt"
val topics = TopicGroup(f)
```  

Non-empty lines are parsed into [entries](../topicEntry/):

```scala mdoc
assert(topics.entries.size == 15)
```

A `TopicGroup` can group daily entries together into a Vector of [`Week`s](../week/), composed of a specified number of weekly class meetings.


```scala mdoc
// Class meets 3 times/week:
topics.weekly(3)
```

If you want to cluster your weeks by subheadings, use the `weeklyClustered`  method.  The result is a Vector of Vectors of `Week`s.


```scala mdoc
// Class meets 3 times/week:
topics.weeklyClustered(3)
```
