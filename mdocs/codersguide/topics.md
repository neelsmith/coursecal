---
title: "Topics"
layout: page
---

```scala mdoc:invisible
import edu.holycross.shot.coursecal._

import java.time._
import java.time.format._
```

We can load topics from a text file. Each non-empty line is either an entry for a class day, or a header for a section of the course.  The difference between the two is that headers start with one or more `#` characters indiciating the header level in markdown output.

```scala mdoc:silent
val f = "src/test/resources/greek101.txt"
val topics = Topics(f)
```  

Non-empty lines are parsed into [entries](../topicEntry/):

```scala mdoc
assert(topics.entries.size == 15)
```
