---
title: "Topics"
layout: page
---

```scala mdoc:invisible
import edu.holycross.shot.coursecal._

import java.time._
import java.time.format._
```

We can load topics from a file.

```scala mdoc:silent
val f = "src/test/resources/greek101.txt"
val topics = Topics(f)
```  

They have a Vector of `TopicEntry`s:

```scala mdoc
assert(topics.entries.size == 15)
```

`TopicEntry`s can include headers and class days.  Get the class days:

```scala mdoc
assert(topics.days.size == 12)
```


### SectionTopics

These can be built from MD strings pound-sign headers:

```scala mdoc
val topic = SectionTopic("## Part 2")
assert(topic.level == 2)
assert(topic.title == "Part 2")
```
