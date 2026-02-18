import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WeatherAlertTest {
    public static void main(String[] args) {
        // Example usage of Weather Alert Notification system
        System.out.println("Starting Weather Alert System.");
        System.out.println();

        List<UniversityPersonnel> weatherAlertSubscribers = new ArrayList<>();

        UniversityStudent studentOne = new UniversityStudent();
        studentOne
                .setEmail("tbr53@drexel.edu")
                .setFirstName("Tom")
                .setIdNumber(53)
                .setLastName("Brown");
        studentOne.setNotificationStrategy(new EmailNotificationStrategy(studentOne.getEmail()));

        UniversityStudent studentTwo = new UniversityStudent();
        studentTwo
                .setFirstName("Hailey")
                .setEmail("htb213@drexel.edu")
                .setIdNumber(213)
                .setLastName("Bar")
                .setPhoneNumber("254-123-9583")
                .setNotificationStrategy(new VoiceCallNotificationStrategy(studentTwo.getPhoneNumber()));

        UniversityFaculty facultyOne = new UniversityFaculty();
        facultyOne
                .setFirstName("John")
                .setLastName("Doe")
                .setIdNumber(993)
                .setPhoneNumber("267-444-1122")
                .setEmail("jnd993@faculty.drexel.edu")
                .setNotificationStrategy(new SMSNotificationStrategy(facultyOne.getPhoneNumber()));
        UniversityFaculty facultyTwo = new UniversityFaculty();
        facultyTwo
                .setFirstName("Jane")
                .setLastName("Woe")
                .setPhoneNumber("267-232-1142")
                .setIdNumber(256)
                .setEmail("jfw256@faculty.drexel.edu")
                .setNotificationStrategy(new EmailNotificationStrategy(facultyTwo.getEmail()));

        UniversityStaff staffOne = new UniversityStaff();
        staffOne
                .setFirstName("Bob")
                .setLastName("Way")
                .setPhoneNumber("254-123-9583")
                .setIdNumber(664)
                .setEmail("byw664@staff.drexel.edu")
                .setNotificationStrategy(new SMSNotificationStrategy(staffOne.getPhoneNumber()));
        UniversityStaff staffTwo = new UniversityStaff();
        staffTwo
                .setEmail("nbe223@staff.drexel.edu")
                .setFirstName("Nessy")
                .setLastName("Eobar")
                .setIdNumber(223)
                .setNotificationStrategy(new EmailNotificationStrategy(staffTwo.getEmail()));

        weatherAlertSubscribers.add(studentOne);
        weatherAlertSubscribers.add(studentTwo);
        weatherAlertSubscribers.add(facultyOne);
        weatherAlertSubscribers.add(facultyTwo);
        weatherAlertSubscribers.add(staffOne);
        weatherAlertSubscribers.add(staffTwo);

        WeatherAlert weatherAlert = new WeatherAlert(new WeatherDecisionEngine());
        System.out.println("Adding subscribers to Weather Alert System.");

        for (UniversityPersonnel person : weatherAlertSubscribers) {
            weatherAlert.registerObserver(person);
        }

        System.out.println("Subscribers of the Weather Alert System:");
        for (UniversityPersonnel subscriber : weatherAlertSubscribers){
            System.out.println(subscriber);
        }
        System.out.println();

        for (int i = 0; i < 2; i++){
            System.out.println("Checking for any weather alerts and notifying subscribers.");
            weatherAlert.refresh();
            System.out.println();
        }

        System.out.println("Removing subscribers from Weather Alert System.");
        Random random = new Random();
        for (int i = 0; i < 2; i++){
            UniversityPersonnel subscriber = weatherAlertSubscribers.get(random.nextInt(weatherAlertSubscribers.size()));
            System.out.println("Removing " + subscriber);
            weatherAlertSubscribers.remove(subscriber);
        }

        System.out.println();
        System.out.println("Last check for any weather alerts and notifying subscribers.");
        weatherAlert.refresh();

        System.out.println();
        System.out.println("Weather Alert System closed.");
    }
}
