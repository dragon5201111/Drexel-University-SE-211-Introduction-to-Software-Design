public class WeatherAlertTest {
    public static void main(String[] args) {
        // Example usage of WeatherAlert class

        Student studentOne = new Student(null);
        studentOne
                .setEmail("tbr53@drexel.edu")
                .setFirstName("Tom")
                .setLastName("Brown");
        studentOne.setNotificationStrategy(new EmailNotificationStrategy(studentOne.getEmail()));

        Faculty facultyOne = new Faculty(null);
        facultyOne
                .setFirstName("John")
                .setLastName("Doe")
                .setPhoneNumber("267-444-1122")
                .setNotificationStrategy(new SMSNotificationStrategy(facultyOne.getPhoneNumber()));

        WeatherAlert weatherAlert = new WeatherAlert(new WeatherDecisionEngine());
        weatherAlert.registerObserver(studentOne);
        weatherAlert.registerObserver(facultyOne);
        weatherAlert.refresh();
    }
}
