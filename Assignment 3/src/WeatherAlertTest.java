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
        Faculty facultyTwo = new Faculty();
        facultyTwo
                .setFirstName("Jane")
                .setLastName("Woe")
                .setPhoneNumber("267-232-1142")
                .setEmail("jfw256@faculty.drexel.edu")
                .setNotificationStrategy(new EmailNotificationStrategy(facultyTwo.getEmail()));

        WeatherAlert weatherAlert = new WeatherAlert(new WeatherDecisionEngine());

        weatherAlert.registerObserver(studentOne);
        weatherAlert.registerObserver(studentTwo);
        weatherAlert.registerObserver(facultyOne);
        System.out.println("Checking for any weather alerts and notifying subscribers.");
        weatherAlert.refresh();

        weatherAlert.registerObserver(facultyTwo);
        weatherAlert.removeObserver(studentOne);
        System.out.println("Checking for any weather alerts and notifying subscribers.");
        weatherAlert.refresh();
    }
}
