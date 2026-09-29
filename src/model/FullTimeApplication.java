package model;

public class FullTimeApplication extends Application {

    private double salary;
    private String employmentType;

    public FullTimeApplication(
            String companyName,
            String role,
            String location,
            String workMode,
            String applicationDate,
            String notes,
            double salary,
            String employmentType) {

        super(
                companyName,
                role,
                location,
                workMode,
                applicationDate,
                notes
        );

        this.salary = salary;
        this.employmentType = employmentType;
    }

    public double getSalary() {
        return salary;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    @Override
    public String toString() {

        return super.toString()
                + " | Salary: ₹"
                + salary
                + " | Type: "
                + employmentType;
    }
}