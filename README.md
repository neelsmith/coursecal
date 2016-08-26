# coursecal

A scala scripting system (using the awesome [Ammonite](http://www.lihaoyi.com/Ammonite/)) to generate course calendars from simple text sources.


## Usage

    amm cal.sc YR MO DA SCHEDULE FILEPATH

`YR`, `MO` and `DA` are integers for a starting date.  The starting date can be any day in the week you want to begin your calendar.

`SCHEDULE` is one of `tt` or `mwf`.

`FILEPATH` is a relative or absolute path to a plain-text file with your daily schedule.

## The file format

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


*Additional notes* may include markdown formatting
