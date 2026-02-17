public class WeatherAlertTest {
    public static void main(String[] args) {
        // Example usage of Weather Alert Notification system
        System.out.println("Subscribing Students, Faculty, and Staff to the Weather Alert System.");
        System.out.println();

        UniversityStudent studentOne = new UniversityStudent();
        studentOne
                .setEmail("tbr53@drexel.edu")
                .setFirstName("Tom")
                .setLastName("Brown");
        studentOne.setNotificationStrategy(new EmailNotificationStrategy(studentOne.getEmail()));

        UniversityStudent studentTwo = new UniversityStudent();
        studentTwo
                .setFirstName("Hailey")
                .setLastName("Bar")
                .setPhoneNumber("254-123-9583")
                .setNotificationStrategy(new VoiceCallNotificationStrategy(studentTwo.getPhoneNumber()));

        UniversityFaculty facultyOne = new UniversityFaculty();
        facultyOne
                .setFirstName("John")
                .setLastName("Doe")
                .setPhoneNumber("267-444-1122")
                .setNotificationStrategy(new SMSNotificationStrategy(facultyOne.getPhoneNumber()));
        UniversityFaculty facultyTwo = new UniversityFaculty();
        facultyTwo
                .setFirstName("Jane")
                .setLastName("Woe")
                .setPhoneNumber("267-232-1142")
                .setEmail("jfw256@faculty.drexel.edu")
                .setNotificationStrategy(new EmailNotificationStrategy(facultyTwo.getEmail()));

        UniversityStaff staffOne = new UniversityStaff();
        staffOne
                .setFirstName("Bob")
                .setLastName("Way")
                .setPhoneNumber("254-123-9583")
                .setNotificationStrategy(new SMSNotificationStrategy(staffOne.getPhoneNumber()));

        WeatherAlert weatherAlert = new WeatherAlert(new WeatherDecisionEngine());

        weatherAlert.registerObserver(studentOne);
        weatherAlert.registerObserver(studentTwo);
        weatherAlert.registerObserver(facultyOne);
        weatherAlert.registerObserver(staffOne);

        System.out.println("Checking for any weather alerts and notifying subscribers.");
        weatherAlert.refresh();
        System.out.println();

        System.out.println("Adding Faculty: " + facultyTwo.getFirstName() + " to the Weather Alert System.");
        weatherAlert.registerObserver(facultyTwo);
        System.out.println("Removing Student: " + studentOne.getFirstName() +  " from the Weather Alert System.");
        weatherAlert.removeObserver(studentOne);
        System.out.println();

        System.out.println("Checking for any weather alerts and notifying subscribers.");
        weatherAlert.refresh();
    }
}
