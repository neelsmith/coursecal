---
title: "The calendar file"
layout: page
parent: "User's guide"
---


## The calendar configuration file

The calendar configuration file is in yaml format.  Lines beginning with `#` are treated as comments.

The configuration must define four values:

- `pageTitle` A String used as the title of the web page generated for a schedule
- `firstDay` A date formatted as `YYYY-MM-DD` identifying the first day of class
- `schedule` A String identifying the meeting pattern.  The following patterns are recognized:  `mwf`, `tt`, `wf`
- `totalWeeks` An integer for the number of *calendar* weeks this schedule should run for.


### Example

```text
# Configure a course calendar
# Used for title of web page
pageTitle : "Latin 101, F'20: course schedule"
# YYYY-MM-DD
firstDay: "2020-09-02"

# Pattern of class meetings: "mwf", "tt" or "wf"
schedule : "mwf"

# Number of weeks the calendar should run for
totalWeeks : 14
```


### Optional fixed dates


```text
fixedDates:
  - 2020-03-03 == Spring break
  - 2020-03-05 == Spring break
  - 2020-05-05 == Exams begin
  - 2020-04-21 == Academic conference on Wednesday
  - 2020-01-30 == [Module 1](../labs/module1) due on Friday
  - 2020-03-12 == [Module 2](../labs/module2) due on Thurs., Mar. 12
  - 2020-04-07 == Tuesday, last day to submit revisions of modules 1-4.
  - 2020-04-09 == Thursday, Easter break.
  - 2020-04-30 == NB! Last day to submit final projects is Wed., May 6.
```
