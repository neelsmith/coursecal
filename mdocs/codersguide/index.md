---
title: "Programmer's guide"
layout: page
nav_order: 2
has_children: true
---


### Imports

```scala mdoc
import edu.holycross.shot.coursecal._
```

### The central class: `Schedule`

You should be able to do everything you want to do using a [`Schedule`](./schedule/), since it includes members of the classes listed here that provide access to all of your data.



### Parsed versions of your source data


- the [`TopicGroup`](./topicGroup/) class represents the raw data in your topics file
- the [`CalendarConfig`](./calendarConfig/) class represents the raw data in your calendrical configuration file

### Content of course material


- the [`Week`](week/) class




### Scheduled course meetings

Structures combining course material and calendrical information, from lowest level to highest:

- the [`DatedWeekMeetings`](datedWeekMeetings/) class
- the [`DatedWeek`](datedWeek/) class
- the [`DatedSegment`](datedSegment/) class
- the [`Schedule`](schedule/) class
