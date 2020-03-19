---
title: "Topics"
layout: page
---


We can load topics from a text file. Each non-empty line is either an entry for a class day, or a header for a section of the course.  The difference between the two is that headers start with one or more `#` characters indiciating the header level in markdown output.

```scala
val f = "src/test/resources/greek101.txt"
val topics = Topics(f)
```  

Non-empty lines are parsed into `TopicEntry`s:

```scala
assert(topics.entries.size == 15)
```

`TopicEntry`s can include headers and class days.  Get the class days:

```scala
assert(topics.days.size == 12)
```


### SectionTopics

These are built from markdown strings with pound-sign headers.

```scala
val topic = SectionTopic("## Part 2")
// topic: SectionTopic = SectionTopic(2, "Part 2")
assert(topic.level == 2)
assert(topic.title == "Part 2")
```