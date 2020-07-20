---
title: "TopicGroup"
layout: page
parent: "Programmer's guide"
nav_order: 1
---


We can load topics from a text file. Each non-empty line is either an entry for a class day, or a header for a section of the course.  The difference between the two is that headers start with one or more `#` characters indiciating the header level in markdown output.

```scala
val f = "src/test/resources/greek101.txt"
val topics = TopicGroup(f)
```  

Non-empty lines are parsed into [entries](../topicEntry/):

```scala
assert(topics.entries.size == 15)
```
