---
title: "CourseDay"
layout: page
parent: "Programmer's guide"
---

```scala mdoc:invisible
import edu.holycross.shot.coursecal._
```

```scala mdoc:invisible
import edu.holycross.shot.coursecal._

import java.time._
import java.time.format._
```


```scala mdoc:silent
val f = "src/test/resources/greek101.txt"
val topics = TopicGroup(f)
```  

The `CourseDay` class
