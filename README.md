# coursecal

A scala scripting system to generate course calendars from simple text sources.


## Prerequisites

- [scala](http://www.scala-lang.org/)
- [sbt](http://www.scala-sbt.org/)
- [ammonite](http://www.lihaoyi.com/Ammonite/)

## Usage

    cal.sc SYLLABUSFILE

or

    cal.sc SYLLABUSFILE CONFIGFILE

If `CONFIGFILE` is not given, `cal.sc` will use `config.yaml`.



## The syllabus file

Content for the daily schedule is in a plain text file with the record for each day on a single line. The structure of the plain text file is as follows:

1. Lines beginning with one or more `#` characters are section headers
2. Empty lines are omitted
3. Content for one day's schedule should be on a single line in three columns delimited by `#`:
    1. Descriptive title or label for entry
    2. URL to assignment (possibly empty)
    3. Additional notes

Leading and trailing white space is trimmed in all columns.

*Descriptive labels* may be annotated with the following annotations that are translated into distinct CSS classes in HTML output:

- `@none` No assignment due that day.
- `@null` No class meeting on that date.


*Additional notes* may include markdown formatting.


## The config file

`pageTitle`

`yr`, `mo` and `da` are integers for a starting date falling anywhere int he first week you want to include in your calendar.  

`schedule` is one of `tt` or `mwf`.

`totalWeeks` is the number of weeks to include in your calendar.
