# xarlsr-utilities

### Overview

It's a library to help developers with the tasks of getting user's inputs from console and with the handling of dates.  

It implements two classes:

- ConsoleInput.class
- DateUtils.class

### Classes

#### ConsoleInput class

A class containing the methods to get several types of data from the console:

- `readChar()`: Reads a char from console and returns it, verifying it's a valid char.
- `readString()`: Reads a string line from console.
- `readString(int)`: Reads a string line from console. Returns a `String` with the maximum length of `int`, truncating the rest if the entered line is longer.
- `readInteger()`: Reads a line from console, checks if its a number, parses it to `int` and returns it.
- `readInteger(int)`: Same as `readInteger()`, but it truncates the integer to the max length set by `int`.
- `readDouble()`: Reads a line from console, check if it's a valid number and returns a `Double` value.
- `pressEnter()`: Ask the user to press ENTER key to continue.
There is another method that does not get any data from console nor returns anything, but it's a helpful method when console needs to be cleared:
- `clearConsole()`: Clears the console.

#### DateUtils class

A class containing several methods to handle dates.

- `stringToDate(String)`: Converts a `String` representing a date in default `BASIC_ISO_DATE` format (yyyyMMdd) in a `LocalDate` object with that date.
- `stringToDate(String, String)`: Converts a `String` representing a date in used defined format in a `LocalDate` object with that date.
- `dateToString(LocalDate)`: Converts a `LocalDate` date in a `String` with the predefined `BASIC_ISO_DATE` format.
- `dateToString(LocalDate, String)`: Converts a `LocalDate` date in a `String` with a user defined format.
- `dateFormatChanger(String,String,String)`: Convert Strings representing dates from a format to other.
- `generateRandomDate(LocalDate,LocalDate)`: Generates a random date between two user selected dates.
- `isValidDate(String)`: Checks if a String representing a strDate in `BASIC_ISO_DATE` format (yyyyMMdd) is valid or not.

For more details visit the [javadoc site](apidocs/dev/xarlsr/utilities/package-summary.html)