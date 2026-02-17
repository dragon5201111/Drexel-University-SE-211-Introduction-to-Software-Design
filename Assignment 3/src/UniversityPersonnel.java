public abstract class UniversityPersonnel extends Notifiable {
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phoneNumber;

    private int age;
    private int idNumber;
    private int wage; // Wage isn't cohesive but, accessor methods can be overridden in desired subclasses to throw an error

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

    UniversityPersonnel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    UniversityPersonnel setWage(int wage) {
        this.wage = wage;
        return this;
    }

    String getFirstName() {
        return this.firstName;
    }

    String getLastName() {
        return this.lastName;
    }

    String getAddress() {
        return this.address;
    }

    String getEmail() {
        return this.email;
    }

    String getPhoneNumber() {
        return this.phoneNumber;
    }

    int getAge() {
        return this.age;
    }

    int getIdNumber() {
        return this.idNumber;
    }

    int getWage() {
        return this.wage;
    }
}
