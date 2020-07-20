---
title: "Segment"
layout: page
parent: "Programmer's guide"
---

```scala mdoc:invisible
import edu.holycross.shot.coursecal._

import java.time._
import java.time.format._
```

The `DatedSegment` class is a labelled part of a course calendar.

It has a `weeks` member, a Vector of [dated course weeks](../datedWeek).
