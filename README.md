# Munro Library Challenge API
This is an API which provides the ability to search munro data.

### Prerequisites
- Scala 2.13.1
- Java 8
- sbt > 1.3.13

### Development Setup
The service binds to localhost only and runs on port 9000

Run from the console using: `sbt run`

## Highlighted SBT Tasks
Task | Description | Command
:-------|:------------|:-----
test | Runs the standard unit tests | ```sbt test```
jacoco | Runs code coverage analysis | ```sbt jacoco```

## API Query Parameters
All query parameters are optional.

Name | Description | Permissible Values
:-------|:------------|:---------------
category | The hill category on which to search on | ```munro```<br />```munro top```<br />```munro,munro top```<br />```munro top,munro```
limit | The maximum number of items to return | ```Any integer value greater than zero```
minHeight | Return items which have a height in meters greater than or equal to the specified value | ```Any decimal or integer value greater than zero```
maxHeight | Return items which have a height in meters less than or equal to the specified value | ```Any decimal or integer value greater than zero```
sort | The sort order for the search results | ```heightInMeters asc```<br />```heightInMeters desc```<br />```name asc```<br />```name desc```
