---
title: "TopicEntry"
layout: page
---


Load some topics into a `TopicGroup` structure.

```scala
val f = "src/test/resources/greek101.txt"
val topics = TopicGroup(f)
```  

Non-empty lines are parsed into `TopicEntry`s:

```scala
assert(topics.entries.size == 15)
```

`TopicEntry`s can include headers and class days.  



## `CourseDay`s

Get the class meeting days:

```scala
assert(topics.days.size == 12)
```


## `SectionTopic`s

These are built from markdown strings with pound-sign headers.

```scala
val topic = SectionTopic("## Part 2")
// topic: SectionTopic = SectionTopic(2, "Part 2")
assert(topic.level == 2)
assert(topic.title == "Part 2")
```
