---
title: "The topics file"
layout: page
parent: "User's guide"
---



# The topics file

The topics file is a line-oriented file, where you can

1. list daily topics for your semester
2. group topics in sections.  

List each *daily topic* on a single line;  empty lines are ignored.  You may include markdown in your topic description.  You could, for example, link a topic to an assignment. The topic description may include tags beginning with `@` that are not parsed as part of the topic label, but associate a special value with a day.  Defined tags at present are: `@none`, meaning "No class meeting this day."

To *group topics* into sections, add a line with a markdown header: any lines beginning with one or more `#` are treated as (sub)section headings.  

### Structure of daily topics

In addition to the markdown describing the day's topic,k you can optionally include a note or reminder that will be added to the week's summary.  To include a note, separate it from the topic description with a `#` character.

Example:

```text
First-year advising @none #
[Introduction to course](assignment1)
[Second-declension nouns](assignment2) # Quiz on Friday
```

In these three entries, the first entry has a tag indicating that class will not be held that day.  The second entry has only a topic for the day (including a link to an assignment);  the third entry has both a topic for the day and a reminder/note to added to the week's summary.


### Structure of header lines

The number of `#`s gives the level of the heading, just like markdown. Example:

```text
## Part 2
```

defines a second-level heading with label `Part 2`



### Composite example

```text
## Week 1
First-year advising @none #
[Introduction to course](assignment1)
[Second-declension nouns](assignment2) # Quiz on Friday


## Week 2
...
```
