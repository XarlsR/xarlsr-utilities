# xarlsr-utilities
[![Latest release](https://img.shields.io/badge/release-3.0.0-blue)](https://github.com/XarlsR/xarlsr-utilities/releases/tag/v3.0.0)

### Overview

**xarls-utilities** is a small toolkit to help developers with the tasks of getting user's inputs from console or any other `InputStream` object, and with the handling of dates in java programming.

Implements two classes with specific methods for each purpose:

- ConsoleInput.class
- DateUtils.class

### Classes

#### ConsoleInput class

A class containing the methods to get several types of data from a InputStream.

If a reading from keyboard is the desired input, the default System.in must be passed as parameter or, as an option, the caller class or method should be the responsible to set the InputStream to default System.in. 

In  case other InputStram is desired as source, any of the `InputStream` classes of the `InputStream` superclass can be used.

Available methods:

- `readString(InputStream)`: Reads a String line from a selected InputStream and returns the result.

This is the base method for reading the input data from the InputStream. Every other method reads the data via `readString(InputStream)` and then parse them
to de desired output type.

- `readString(InputStream, int)`: Same as `readString(InputStream)` method but it returns a `String` with the maximum length of `int`, truncating the rest if the entered line is longer.


- `readChar(InputStream)`: Reads a char from console and returns it, verifying it's a valid char.


- `readInteger(InputStream)`: Reads a line from console, checks if its a number, parses it to `int` and returns it.


- `readInteger(InputeStream, int)`: Same as `readInteger()`, but it truncates the integer to the max length set by `int`.


- `readDouble(InputStream)`: Reads a line from console, check if it's a valid number and returns a `Double` value.


- `pressEnter()`: Ask the user to press ENTER key to continue.


  There is another method that does not get any data from console nor returns anything, but it's a helpful method when console needs to be cleared:
- `clearConsole()`: Clears the console in Windows systems.

#### DateUtils class

A class containing several methods to handle dates.

- `stringToDate(String)`: Converts a `String` representing a date in default `BASIC_ISO_DATE` format (yyyyMMdd) in a `LocalDate` object with that date.
- `stringToDate(String, String)`: Converts a `String` representing a date in used defined format in a `LocalDate` object with that date.
- `dateToString(LocalDate)`: Converts a `LocalDate` date in a `String` with the predefined `BASIC_ISO_DATE` format.
- `dateToString(LocalDate, String)`: Converts a `LocalDate` date in a `String` with a user defined format.
- `dateFormatChanger(String,String,String)`: Convert Strings representing dates from a format to other.
- `generateRandomDate(LocalDate,LocalDate)`: Generates a random date between two user selected dates.
- `isValidDate(String)`: Checks if a String representing a strDate in `BASIC_ISO_DATE` format (yyyyMMdd) is valid or not.


