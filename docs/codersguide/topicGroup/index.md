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

A `TopicGroup` can group daily entries together into a Vector of [`Week`s](../week/), composed of a specified number of weekly class meetings.


```scala
// Class meets 3 times/week:
topics.weekly(3)
// res1: Vector[Week] = Vector(
//   Week(
//     Vector(
//       CourseDay("Advising @none", "", Vector()),
//       CourseDay("[Class 1](assignment1)", "Note on week", Vector()),
//       CourseDay(
//         "[Class 2](assignment2)",
//         "Special activity this weekend",
//         Vector()
//       )
//     ),
//     3
//   ),
//   Week(
//     Vector(
//       CourseDay("The second declension", "", Vector()),
//       CourseDay("The first declension", "", Vector()),
//       CourseDay("The third declension", "", Vector())
//     ),
//     3
//   ),
//   Week(
//     Vector(
//       CourseDay("Irregular third-declension nouns", "", Vector()),
//       CourseDay("Mid term", "Exam", Vector()),
//       CourseDay("Vacation @none", "", Vector())
//     ),
//     3
//   ),
//   Week(
//     Vector(
//       CourseDay("First-second adjectives", "Quiz Friday", Vector()),
//       CourseDay("Third-declension adjectives", "", Vector()),
//       CourseDay("Adverbs", "", Vector())
//     ),
//     3
//   )
// )
```

If you want to cluster your weeks by subheadings, use the `weeklyClustered`  method.  The result is a Vector of Vectors of `Week`s.


```scala
// Class meets 3 times/week:
topics.weeklyClustered(3)
// res2: Vector[Vector[Week]] = Vector(
//   Vector(
//     Week(
//       Vector(
//         CourseDay("Advising @none", "", Vector()),
//         CourseDay("[Class 1](assignment1)", "Note on week", Vector()),
//         CourseDay(
//           "[Class 2](assignment2)",
//           "Special activity this weekend",
//           Vector()
//         )
//       ),
//       3
//     )
//   ),
//   Vector(
//     Week(
//       Vector(
//         CourseDay("The second declension", "", Vector()),
//         CourseDay("The first declension", "", Vector()),
//         CourseDay("The third declension", "", Vector())
//       ),
//       3
//     ),
//     Week(
//       Vector(
//         CourseDay("Irregular third-declension nouns", "", Vector()),
//         CourseDay("Mid term", "Exam", Vector()),
//         CourseDay("Vacation @none", "", Vector())
//       ),
//       3
//     )
//   ),
//   Vector(
//     Week(
//       Vector(
//         CourseDay("First-second adjectives", "Quiz Friday", Vector()),
//         CourseDay("Third-declension adjectives", "", Vector()),
//         CourseDay("Adverbs", "", Vector())
//       ),
//       3
//     )
//   )
// )
```
