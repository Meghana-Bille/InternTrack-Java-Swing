package model;

import interfaces.Searchable;
import interfaces.Trackable;

public class Application implements Trackable, Searchable {

    private static int nextId = 1001;

    private final int applicationId;
    private String companyName;
    private String role;
    private String location;
    private String workMode;
    private String applicationDate;
    private String status;
    private String notes;

    public Application(
            String companyName,
            String role,
            String location,
            String workMode,
            String applicationDate,
            String notes) {

        this.applicationId = nextId++;

        this.companyName = companyName;
        this.role = role;
        this.location = location;
        this.workMode = workMode;
        this.applicationDate = applicationDate;

        // A new application is automatically marked as Applied.
        this.status = "Applied";

        this.notes = notes;
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

    public String getLocation() {
        return location;
    }

    public String getWorkMode() {
        return workMode;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public String getStatus() {
        return status;
    }

    public String getNotes() {
        return notes;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setWorkMode(String workMode) {
        this.workMode = workMode;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public void updateStatus(String status) {

        this.status = status;
    }

    @Override
    public boolean matches(String keyword) {

        keyword = keyword.toLowerCase();

        return companyName.toLowerCase().contains(keyword)
                || role.toLowerCase().contains(keyword)
                || status.toLowerCase().contains(keyword);
    }

    @Override
    public String toString() {

        return applicationId
                + " - "
                + companyName
                + " - "
                + role
                + " - "
                + status;
    }
}