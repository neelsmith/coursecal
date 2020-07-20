---
title: "Schedule"
layout: page
parent: "Programmer's guide"
---


The `Schedule` class is where the magic happens: it brings together a [collection of topics](../topics/) and a [configuration](../calendarConfig/) for a given semester-long course.

Build it from text files:




## Some stuff it has

It can cluster topics into a series of [[Week]]s by segments labelled with headings

```scala
sched.weeklyClustered
// res0: Vector[Vector[Week]] = Vector(
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

A list of `DatedSegment`s

```scala
sched.segments
// res1: Vector[DatedSegment] = Vector(
//   DatedSegment(
//     Vector(
//       DatedWeek(
//         Week(
//           Vector(
//             CourseDay("Advising @none", "", Vector()),
//             CourseDay("[Class 1](assignment1)", "Note on week", Vector()),
//             CourseDay(
//               "[Class 2](assignment2)",
//               "Special activity this weekend",
//               Vector()
//             )
//           ),
//           3
//         ),
//         MonWedFriWeek(2017-08-21),
//         Vector()
//       )
//     ),
//     0,
//     Some(SectionTopic(2, "Section 1: introduction"))
//   ),
//   DatedSegment(
//     Vector(
//       DatedWeek(
//         Week(
//           Vector(
//             CourseDay("The second declension", "", Vector()),
//             CourseDay("The first declension", "", Vector()),
//             CourseDay("The third declension", "", Vector())
//           ),
//           3
//         ),
//         MonWedFriWeek(2017-08-28),
//         Vector(FixedEvent(2017-08-28, "First-year advising."))
//       ),
//       DatedWeek(
//         Week(
//           Vector(
//             CourseDay("Irregular third-declension nouns", "", Vector()),
//             CourseDay("Mid term", "Exam", Vector()),
//             CourseDay("Vacation @none", "", Vector())
//           ),
//           3
//         ),
//         MonWedFriWeek(2017-09-04),
//         Vector()
//       )
// ...
```

```scala
sched.segments.map(s => s.weeks)
// res2: Vector[Vector[DatedWeek]] = Vector(
//   Vector(
//     DatedWeek(
//       Week(
//         Vector(
//           CourseDay("Advising @none", "", Vector()),
//           CourseDay("[Class 1](assignment1)", "Note on week", Vector()),
//           CourseDay(
//             "[Class 2](assignment2)",
//             "Special activity this weekend",
//             Vector()
//           )
//         ),
//         3
//       ),
//       MonWedFriWeek(2017-08-21),
//       Vector()
//     )
//   ),
//   Vector(
//     DatedWeek(
//       Week(
//         Vector(
//           CourseDay("The second declension", "", Vector()),
//           CourseDay("The first declension", "", Vector()),
//           CourseDay("The third declension", "", Vector())
//         ),
//         3
//       ),
//       MonWedFriWeek(2017-08-28),
//       Vector(FixedEvent(2017-08-28, "First-year advising."))
//     ),
//     DatedWeek(
//       Week(
//         Vector(
//           CourseDay("Irregular third-declension nouns", "", Vector()),
//           CourseDay("Mid term", "Exam", Vector()),
//           CourseDay("Vacation @none", "", Vector())
//         ),
//         3
//       ),
//       MonWedFriWeek(2017-09-04),
//       Vector()
//     )
//   ),
//   Vector(
//     DatedWeek(
//       Week(
//         Vector(
// ...
```
