public abstract class UniversityPersonnel extends Notifiable {
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phoneNumber;

    private int age;
    private int idNumber;
    private int wage; // Wage isn't cohesive but, accessor methods can be overridden in desired subclasses to throw an error

    public UniversityPersonnel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UniversityPersonnel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UniversityPersonnel setAddress(String address) {
        this.address = address;
        return this;
    }

    public UniversityPersonnel setEmail(String email) {
        this.email = email;
        return this;
    }

    public UniversityPersonnel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public UniversityPersonnel setAge(int age) {
        this.age = age;
        return this;
    }

    public UniversityPersonnel setIdNumber(int idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public UniversityPersonnel setWage(int wage) {
        this.wage = wage;
        return this;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getAddress() {
        return this.address;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public int getAge() {
        return this.age;
    }

    public int getIdNumber() {
        return this.idNumber;
    }

    public int getWage() {
        return this.wage;
    }
}
