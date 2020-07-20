---
title: "CalendarConfig"
layout: page
parent: "Programmer's guide"
nav_order: 2
---

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

The `CalendarConfig` class
