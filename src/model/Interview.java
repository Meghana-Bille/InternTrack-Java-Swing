package model;

public class Interview {

    private String companyName;
    private String role;
    private String interviewDate;
    private String round;
    private String mode;
    private String result;
    private String notes;

    public Interview(String companyName,
                     String role,
                     String interviewDate,
                     String round,
                     String mode,
                     String result,
                     String notes) {

        this.companyName = companyName;
        this.role = role;
        this.interviewDate = interviewDate;
        this.round = round;
        this.mode = mode;
        this.result = result;
        this.notes = notes;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getRole() {
        return role;
    }

    public String getInterviewDate() {
        return interviewDate;
    }

    public String getRound() {
        return round;
    }

    public String getMode() {
        return mode;
    }

    public String getResult() {
        return result;
    }

    public String getNotes() {
        return notes;
    }

    public void setInterviewDate(String interviewDate) {
        this.interviewDate = interviewDate;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {

        return companyName +
                " - " +
                role +
                " - Round: " +
                round +
                " - Result: " +
                result;
    }
}