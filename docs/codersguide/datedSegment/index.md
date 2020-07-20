---
title: "Segment"
layout: page
parent: "Programmer's guide"
---




The `DatedSegment` class is a labelled part of a course calendar.  You'll see this in the `segments` member of a [schedule](../schedule/).

```scala
// How many labelled segments are in this schedule?
schedule.segments.size
// res0: Int = 4
```

Each `DatedSegment` has a `weeks` member, which is a Vector of [dated course weeks](../datedWeek/).


```scala
val firstSegment = schedule.segments.head
// firstSegment: DatedSegment = DatedSegment(
//   Vector(
//     DatedWeek(
//       Week(
//         Vector(
//           CourseDay("NO CLASSES", "", Vector()),
//           CourseDay("Introduction to course", "", Vector()),
//           CourseDay("Nouns", "", Vector())
//         ),
//         3
//       ),
//       MonWedFriWeek(2020-08-24),
//       Vector()
//     ),
//     DatedWeek(
//       Week(
//         Vector(
//           CourseDay("Adjectives", "", Vector()),
//           CourseDay("Demonstratives", "", Vector()),
//           CourseDay("-ius adjectives", "", Vector())
//         ),
//         3
//       ),
//       MonWedFriWeek(2020-08-31),
//       Vector()
//     )
//   ),
//   0,
//   Some(SectionTopic(2, "Latin nouns and adjectives"))
// )
// How many weeks are in the first segment?
firstSegment.weeks.size
// res1: Int = 2
```
