---
title: "The coursecal library: user's guide"
layout: page
---


You can manage your course schedule using two files:

- a [calendar configuration file](calendarFile/), with information specific to a particular semester.  This file is in `yaml` format, and configures a schedule for a particular semester, with information like what the course meeting pattern is (e.g., MWF?  TR?), when the term begins, and how many weeks it runs.
- a [topics file](topics/).  This is a simple text file with one line in markdown format for each class meeting.  The topics file has no information specific to the calendar or schedule of a particular semester, so you can easily reuse it or modify for future offerings of the same course.




Here are some old notes from the project wiki that should be updated and properly run here:

Initial notes on how to use this.

What you need:

- a `.yaml` file configuring your course for a particular semester
- a text file with a sequence of topics

## Building a calendar page

Create a `Schedule` from the topics file and `.yaml` file

The schedule object can create a markdown page:



## Structure of `.yaml` configuration

## Structure of topics file

- empty lines are ignored
- two types of content lines:  
    1. lines starting with one or more `#` are treated as (sub)section headings like markdown headers.  
    2. other lines are treated as the entry for a given day.

### Structure of header lines

The number of `#`s gives the level of the heading, just like markdown. Example:

    ## Part 2

defines a second-level heading with label `Part 2`

### Structure of day entries

- may have up to two `#`-delimited parts:  the topic of the day; weekly notes or reminders
- the topic may include tags that are not parsed as part of the topic label, but associate a value with a day.  Defined tags at present are: `@none`, meaning "No class meeting this day."  

Example:


    First-year advising @none #
    [Introduction to course](assignment1)
    [Second-declension nouns](assignment2) # Quiz on Friday

In these three entries, the first entry has a tag indicating that class will not be held that day.  The second entry has only a topic for the day;  the third entry has both a topic for the and a reminder/note.


### Composite example


    ## Week 1
    First-year advising @none #
    [Introduction to course](assignment1)
    [Second-declension nouns](assignment2) # Quiz on Friday
