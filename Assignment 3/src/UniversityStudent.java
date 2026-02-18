public class UniversityStudent extends UniversityPersonnel {
    // Specialized fields and methods for Student here (e.g., classes)
    @Override
    public int getWage() {
        throw new UnsupportedOperationException("Student doesn't have a wage");
    }
}
