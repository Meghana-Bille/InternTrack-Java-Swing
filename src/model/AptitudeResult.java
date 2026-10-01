package model;

public class AptitudeResult {

    private int applicationId;
    private String companyName;
    private String role;

    private int score;
    private int correctAnswers;
    private int totalQuestions;
    private int percentage;

    private boolean passed;

    private String completedDate;

    public AptitudeResult(
            int applicationId,
            String companyName,
            String role,
            int score,
            int correctAnswers,
            int totalQuestions,
            int percentage,
            boolean passed,
            String completedDate) {

        this.applicationId = applicationId;
        this.companyName = companyName;
        this.role = role;
        this.score = score;
        this.correctAnswers = correctAnswers;
        this.totalQuestions = totalQuestions;
        this.percentage = percentage;
        this.passed = passed;
        this.completedDate = completedDate;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getRole() {
        return role;
    }

    public int getScore() {
        return score;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public int getPercentage() {
        return percentage;
    }

    public boolean hasPassed() {
        return passed;
    }

    public String getCompletedDate() {
        return completedDate;
    }

    @Override
    public String toString() {

        return companyName
                + " - "
                + role
                + " | Score: "
                + score
                + "/"
                + totalQuestions
                + " | "
                + percentage
                + "% | "
                + (passed
                    ? "Passed"
                    : "Rejected");
    }
}