package model;

public class InternshipApplication extends Application {

    private int durationMonths;
    private double stipend;

    public InternshipApplication(
            String companyName,
            String role,
            String location,
            String workMode,
            String applicationDate,
            String notes,
            int durationMonths,
            double stipend) {

        super(
                companyName,
                role,
                location,
                workMode,
                applicationDate,
                notes
        );

        this.durationMonths = durationMonths;
        this.stipend = stipend;
    }

    public int getDurationMonths() {
        return durationMonths;
    }

    public double getStipend() {
        return stipend;
    }

    public void setDurationMonths(int durationMonths) {
        this.durationMonths = durationMonths;
    }

    public void setStipend(double stipend) {
        this.stipend = stipend;
    }

    @Override
    public String toString() {

        return super.toString()
                + " | Duration: "
                + durationMonths
                + " months | Stipend: ₹"
                + stipend;
    }
}
