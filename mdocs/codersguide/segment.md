---
title: "Segment"
layout: page
---

```scala mdoc:invisible
import edu.holycross.shot.coursecal._

import java.time._
import java.time.format._
```

The `Segment` class is a labelled part of a course calendar.

It has a `weeks` member, a Vector of [dated course weeks](../datedWeek).
