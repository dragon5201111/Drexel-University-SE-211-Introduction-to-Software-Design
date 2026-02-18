## Requirements
- Java Version 24.0.1, Language Level 21 is required.

## Running The Weather Alert Test Application
I have provided a test application to demonstrate how the Weather Alert System can
be applied. Note that the implementation of WeatherAlert, WeatherDecisionEngine, and all NotificationStrategy classes are
incomplete (e.g., the WeatherDecisionEngine simulates class cancellations, and doesn't assimilate weather data.)
but provide a framework to which a Weather Alert System could be built upon.

To run the test application do the following:
- Open a CLI, and navigate to the root directory of this assignment (i.e., where src lives).
- Run `javac ./src/*.java -d ./bin`.
- Run `jar cfe WeatherAlertTest.jar WeatherAlertTest -C bin .`.
- Run `java -jar ./WeatherAlertTest.jar` to run the test application.

## Notes
- The Weather Alert Test application instantiates a NotificationStrategy directly object into each UniversityPersonnel
object. In practice, you would want to create a factory class to instantiate the appropriate
NotificationStrategy. However, I have omitted it from this assignment because it is unnecessary for demonstration purposes.