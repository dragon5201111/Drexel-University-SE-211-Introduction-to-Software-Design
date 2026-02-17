## Running The Weather Alert Test Application
I have provided a test application to demonstrate how the Weather Alert System can
be applied. Note that the implementation of WeatherAlert, WeatherDecisionEngine, and all NotificationStrategy classes are incomplete and serve
as a proof of concept.

To run the test application do the following:
- Open a CLI, and navigate to the root directory of this assignment (i.e., where src lives).
- Run `javac ./src/*.java -d ./bin`.
- Run `jar cfe WeatherAlertTest.jar WeatherAlertTest -C bin .`.
- Run `java -jar ./WeatherAlertTest.jar` to run the test application.