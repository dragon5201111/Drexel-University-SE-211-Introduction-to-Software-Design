public class WeatherAlertTest {
    public static void main(String[] args) {
        // Example usage of Weather Alert Notification system
        Student studentOne = new Student();
        studentOne
                .setEmail("tbr53@drexel.edu")
                .setFirstName("Tom")
                .setLastName("Brown");
        studentOne.setNotificationStrategy(new EmailNotificationStrategy(studentOne.getEmail()));

        Student studentTwo = new Student();
        studentTwo
                .setFirstName("Hailey")
                .setLastName("Bar")
                .setPhoneNumber("254-123-9583")
                .setNotificationStrategy(new VoiceCallNotificationStrategy(studentTwo.getPhoneNumber()));

        Faculty facultyOne = new Faculty();
        facultyOne
                .setFirstName("John")
                .setLastName("Doe")
                .setPhoneNumber("267-444-1122")
                .setNotificationStrategy(new SMSNotificationStrategy(facultyOne.getPhoneNumber()));

        WeatherAlert weatherAlert = new WeatherAlert(new WeatherDecisionEngine());
        weatherAlert.registerObserver(studentOne);
        weatherAlert.registerObserver(studentTwo);
        weatherAlert.registerObserver(facultyOne);
        weatherAlert.refresh();
    }
}
