### VERSION 3.0.1

###### - ISSUE I001:
Method `readString(InputStream)` returning Exception after subsequent calls after the first, when the selected `InputStream` were `System.in`.

The found problem is that `readString(InputStream)` closed the `InputStream` after being read. Unlike other `InputStream` objects, `System.in` can't be reopened once close, so, further calls to read this input are rejected with a `Exception`.

The problem's been solved recoding the `readString(InputStrem)` method to close only `InputStreams` different to `System.in`. In case `System.in` was the selected input, it remains open after reading now.
