---
title: "Schedule"
layout: page
parent: "Programmer's guide"
nav_order: 0
---


## The key class: `Schedule`

The `Schedule` class is where the magic happens: it brings together a collection of topics and a calendar configuration for a given semester-long course.



Build a `Schedule` from a pair of text files:

```scala
import edu.holycross.shot.coursecal._
val topics = "src/test/resources/latin101.txt"
// topics: String = "src/test/resources/latin101.txt"
val conf = "src/test/resources/latin101.yaml"
// conf: String = "src/test/resources/latin101.yaml"
val sched = Schedule(topics, conf)
// sched: Schedule = Schedule(
//   TopicGroup(
//     Vector(
//       SectionTopic(2, "Latin nouns and adjectives"),
//       CourseDay("NO CLASSES", "", Vector()),
//       CourseDay("Introduction to course", "", Vector()),
//       CourseDay("Nouns", "", Vector()),
//       CourseDay("Adjectives", "", Vector()),
//       CourseDay("Demonstratives", "", Vector()),
//       CourseDay("-ius adjectives", "", Vector()),
//       SectionTopic(2, "Verbs"),
//       CourseDay("Composition workshop", "", Vector()),
//       CourseDay("Verbs", "", Vector()),
//       CourseDay("Imperfect tense", "", Vector()),
//       CourseDay("Sum, esse, fui", "", Vector()),
//       CourseDay("Possum", "", Vector()),
//       CourseDay("Reading practice", "", Vector()),
//       CourseDay("Review", "", Vector()),
//       CourseDay("NO CLASS", "", Vector()),
//       CourseDay("Composition workshop", "", Vector()),
//       CourseDay("Review", "Composition 2 due.", Vector()),
//       CourseDay("Competency Quiz", "1", Vector()),
//       CourseDay("Competency Quiz", "2", Vector()),
//       SectionTopic(2, "Time, purpose"),
//       CourseDay("Expressions of place and time", "Competency Quiz 3", Vector()),
//       CourseDay("Gerunds and gerundives", "", Vector()),
//       CourseDay("More gerunds and gerundives", "", Vector()),
//       SectionTopic(2, "More verb tense"),
//       CourseDay("Interrogative adjectives and pronouns", "", Vector()),
//       CourseDay("Present active and passive indicative", "", Vector()),
//       CourseDay("Future active and passive indicative", "", Vector()),
//       CourseDay("Imperfect active and passive subjunctive", "", Vector()),
//       CourseDay("Present active and passive subjunctive", "", Vector()),
//       CourseDay("Verb review", "", Vector()),
//       CourseDay("Verb competency quiz 1", "", Vector()),
//       CourseDay("Verb competency quiz 2", "", Vector()),
//       CourseDay("Verbal competency quiz 3", "", Vector()),
//       CourseDay("Deponent verbs, indirect statement", "", Vector()),
//       CourseDay("Personal pronouns and reflexives", "", Vector()),
//       CourseDay("Temporal clauses", "", Vector()),
//       CourseDay(
//         "Perfect and pluperfect active and passive subjunctive",
//         "",
//         Vector()
//       ),
//       CourseDay(
//         "Irregular Verbs: fer\u014d, ferre, tul\u012b, l\u0101tus; e\u014d, \u012bre, i\u012b/\u012bv\u012b, it\u016brus",
//         "",
// ...
```

The parsed data sources are available from the new `Schedule`:

- the `topics` member is the result of parsing the topics file into a [topic group](../topicGroup/)
- the `conf` member is the result of parsing calendar file  into a [calendar configureation](../calendarConfig/)


```scala
sched.topics
// res0: TopicGroup = TopicGroup(
//   Vector(
//     SectionTopic(2, "Latin nouns and adjectives"),
//     CourseDay("NO CLASSES", "", Vector()),
//     CourseDay("Introduction to course", "", Vector()),
//     CourseDay("Nouns", "", Vector()),
//     CourseDay("Adjectives", "", Vector()),
//     CourseDay("Demonstratives", "", Vector()),
//     CourseDay("-ius adjectives", "", Vector()),
//     SectionTopic(2, "Verbs"),
//     CourseDay("Composition workshop", "", Vector()),
//     CourseDay("Verbs", "", Vector()),
//     CourseDay("Imperfect tense", "", Vector()),
//     CourseDay("Sum, esse, fui", "", Vector()),
//     CourseDay("Possum", "", Vector()),
//     CourseDay("Reading practice", "", Vector()),
//     CourseDay("Review", "", Vector()),
//     CourseDay("NO CLASS", "", Vector()),
//     CourseDay("Composition workshop", "", Vector()),
//     CourseDay("Review", "Composition 2 due.", Vector()),
//     CourseDay("Competency Quiz", "1", Vector()),
//     CourseDay("Competency Quiz", "2", Vector()),
//     SectionTopic(2, "Time, purpose"),
//     CourseDay("Expressions of place and time", "Competency Quiz 3", Vector()),
//     CourseDay("Gerunds and gerundives", "", Vector()),
//     CourseDay("More gerunds and gerundives", "", Vector()),
//     SectionTopic(2, "More verb tense"),
//     CourseDay("Interrogative adjectives and pronouns", "", Vector()),
//     CourseDay("Present active and passive indicative", "", Vector()),
//     CourseDay("Future active and passive indicative", "", Vector()),
//     CourseDay("Imperfect active and passive subjunctive", "", Vector()),
//     CourseDay("Present active and passive subjunctive", "", Vector()),
//     CourseDay("Verb review", "", Vector()),
//     CourseDay("Verb competency quiz 1", "", Vector()),
//     CourseDay("Verb competency quiz 2", "", Vector()),
//     CourseDay("Verbal competency quiz 3", "", Vector()),
//     CourseDay("Deponent verbs, indirect statement", "", Vector()),
//     CourseDay("Personal pronouns and reflexives", "", Vector()),
//     CourseDay("Temporal clauses", "", Vector()),
//     CourseDay(
//       "Perfect and pluperfect active and passive subjunctive",
//       "",
//       Vector()
//     ),
//     CourseDay(
//       "Irregular Verbs: fer\u014d, ferre, tul\u012b, l\u0101tus; e\u014d, \u012bre, i\u012b/\u012bv\u012b, it\u016brus",
//       "",
//       Vector()
// ...
sched.conf
// res1: CalendarConfig = CalendarConfig(
//   "Latin 101, F'20: course schedule",
//   2020-09-02,
//   edu.holycross.shot.coursecal.MWF$@698d1a0e,
//   14,
//   Vector(
//     FixedEvent(2020-03-03, "Spring break"),
//     FixedEvent(2020-03-05, "Spring break"),
//     FixedEvent(2020-05-05, "Exams begin"),
//     FixedEvent(2020-04-21, "Academic conference on Wednesday"),
//     FixedEvent(2020-01-30, "[Module 1](../labs/module1) due on Friday"),
//     FixedEvent(2020-03-12, "[Module 2](../labs/module2) due on Thurs., Mar. 12"),
//     FixedEvent(
//       2020-04-07,
//       "Tuesday, last day to submit revisions of modules 1-4."
//     ),
//     FixedEvent(2020-04-09, "Thursday, Easter break."),
//     FixedEvent(
//       2020-04-30,
//       "NB! Last day to submit final projects is Wed., May 6."
//     )
//   )
// )
```



## How it works

The `TopicGroup` by itself can group topics into [`Week`s](./week/), cluster them by  labelled markdown headings.

The `datedTopics` method associates each week of topics with a calendrical week.

```scala
sched.datedTopics
// res2: Vector[DatedWeek] = Vector(
//   DatedWeek(
//     Week(
//       Vector(
//         CourseDay("NO CLASSES", "", Vector()),
//         CourseDay("Introduction to course", "", Vector()),
//         CourseDay("Nouns", "", Vector())
//       ),
//       3
//     ),
//     MonWedFriWeek(2020-09-02),
//     Vector()
//   ),
//   DatedWeek(
//     Week(
//       Vector(
//         CourseDay("Adjectives", "", Vector()),
//         CourseDay("Demonstratives", "", Vector()),
//         CourseDay("-ius adjectives", "", Vector())
//       ),
//       3
//     ),
//     MonWedFriWeek(2020-09-09),
//     Vector()
//   ),
//   DatedWeek(
//     Week(
//       Vector(
//         CourseDay("Composition workshop", "", Vector()),
//         CourseDay("Verbs", "", Vector()),
//         CourseDay("Imperfect tense", "", Vector())
//       ),
//       3
//     ),
//     MonWedFriWeek(2020-09-16),
//     Vector()
//   ),
//   DatedWeek(
//     Week(
//       Vector(
//         CourseDay("Sum, esse, fui", "", Vector()),
//         CourseDay("Possum", "", Vector()),
//         CourseDay("Reading practice", "", Vector())
//       ),
//       3
//     ),
//     MonWedFriWeek(2020-09-23),
//     Vector()
//   ),
// ...
```

A list of `DatedSegment`s

```scala
sched.segments
// res3: Vector[DatedSegment] = Vector(
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
// res4: Vector[Vector[DatedWeek]] = Vector(
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
