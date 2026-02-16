public class UniversityPersonnel extends Recipient {
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private int age;
    private int idNumber;

    public UniversityPersonnel(NotificationStrategy notificationStrategy) {
        super(notificationStrategy);
    }

    UniversityPersonnel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    UniversityPersonnel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    UniversityPersonnel setAddress(String address) {
        this.address = address;
        return this;
    }

    UniversityPersonnel setEmail(String email) {
        this.email = email;
        return this;
    }

    UniversityPersonnel setAge(int age) {
        this.age = age;
        return this;
    }

    UniversityPersonnel setIdNumber(int idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    String getFirstName() {
        return firstName;
    }

    String getLastName() {
        return lastName;
    }

    String getAddress() {
        return address;
    }

    String getEmail() {
        return email;
    }

    int getAge() {
        return age;
    }

    int getIdNumber() {
        return idNumber;
    }
}
