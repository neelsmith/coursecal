---
title: "Schedule"
layout: page
parent: "Programmer's guide"
---


The `Schedule` class is where the magic happens: it brings together a [collection of topics](../topics/) and a [configuration](../calendarConfig/) for a given semester-long course.

Build a `Schedule` from a pair of text files:


## How it works


Entries in the topics file are parsed into a [topic group](../topicGroup/).


It can cluster daily topics together by  labelled markdown headings in the topics file.  The result is unfortunately called a `Week` even though the resulting cluster may be multiple weeks.

> This needs to be changed in an API-breaking update.

```scala
sched.weeklyClustered
// res0: Vector[Vector[Week]] = Vector(
//   Vector(
//     Week(
//       Vector(
//         CourseDay("NO CLASSES", "", Vector()),
//         CourseDay("Introduction to course", "", Vector()),
//         CourseDay("Nouns", "", Vector())
//       ),
//       3
//     ),
//     Week(
//       Vector(
//         CourseDay("Adjectives", "", Vector()),
//         CourseDay("Demonstratives", "", Vector()),
//         CourseDay("-ius adjectives", "", Vector())
//       ),
//       3
//     )
//   ),
//   Vector(
//     Week(
//       Vector(
//         CourseDay("Composition workshop", "", Vector()),
//         CourseDay("Verbs", "", Vector()),
//         CourseDay("Imperfect tense", "", Vector())
//       ),
//       3
//     ),
//     Week(
//       Vector(
//         CourseDay("Sum, esse, fui", "", Vector()),
//         CourseDay("Possum", "", Vector()),
//         CourseDay("Reading practice", "", Vector())
//       ),
//       3
//     ),
//     Week(
//       Vector(
//         CourseDay("Review", "", Vector()),
//         CourseDay("NO CLASS", "", Vector()),
//         CourseDay("Composition workshop", "", Vector())
//       ),
//       3
//     ),
//     Week(
//       Vector(
//         CourseDay("Review", "Composition 2 due.", Vector()),
//         CourseDay("Competency Quiz", "1", Vector()),
//         CourseDay("Competency Quiz", "2", Vector())
// ...
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
//             CourseDay("NO CLASSES", "", Vector()),
//             CourseDay("Introduction to course", "", Vector()),
//             CourseDay("Nouns", "", Vector())
//           ),
//           3
//         ),
//         MonWedFriWeek(2020-08-24),
//         Vector()
//       ),
//       DatedWeek(
//         Week(
//           Vector(
//             CourseDay("Adjectives", "", Vector()),
//             CourseDay("Demonstratives", "", Vector()),
//             CourseDay("-ius adjectives", "", Vector())
//           ),
//           3
//         ),
//         MonWedFriWeek(2020-08-31),
//         Vector()
//       )
//     ),
//     0,
//     Some(SectionTopic(2, "Latin nouns and adjectives"))
//   ),
//   DatedSegment(
//     Vector(
//       DatedWeek(
//         Week(
//           Vector(
//             CourseDay("Composition workshop", "", Vector()),
//             CourseDay("Verbs", "", Vector()),
//             CourseDay("Imperfect tense", "", Vector())
//           ),
//           3
//         ),
//         MonWedFriWeek(2020-09-07),
//         Vector()
//       ),
//       DatedWeek(
//         Week(
//           Vector(
//             CourseDay("Sum, esse, fui", "", Vector()),
// ...
```

```scala
sched.segments.map(s => s.weeks)
// res2: Vector[Vector[DatedWeek]] = Vector(
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
//   Vector(
//     DatedWeek(
//       Week(
//         Vector(
//           CourseDay("Composition workshop", "", Vector()),
//           CourseDay("Verbs", "", Vector()),
//           CourseDay("Imperfect tense", "", Vector())
//         ),
//         3
//       ),
//       MonWedFriWeek(2020-09-07),
//       Vector()
//     ),
//     DatedWeek(
//       Week(
//         Vector(
//           CourseDay("Sum, esse, fui", "", Vector()),
//           CourseDay("Possum", "", Vector()),
//           CourseDay("Reading practice", "", Vector())
//         ),
//         3
//       ),
// ...
```
