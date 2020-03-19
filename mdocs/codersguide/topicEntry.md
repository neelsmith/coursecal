---
title: "TopicEntry"
layout: page
---

```scala mdoc:invisible
import edu.holycross.shot.coursecal._

```

Load some topics into a `TopicGroup` structure.

```scala mdoc:silent
val f = "src/test/resources/greek101.txt"
val topics = TopicGroup(f)
```  

Non-empty lines are parsed into `TopicEntry`s:

```scala mdoc
assert(topics.entries.size == 15)
```

`TopicEntry`s can include headers and class days.  



## `CourseDay`s

Get the class meeting days:

```scala mdoc
assert(topics.days.size == 12)
```


## `SectionTopic`s

These are built from markdown strings with pound-sign headers.

```scala mdoc
val topic = SectionTopic("## Part 2")
assert(topic.level == 2)
assert(topic.title == "Part 2")
```