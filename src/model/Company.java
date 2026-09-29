package model;

import java.util.ArrayList;

public class Company {

    private String companyName;
    private String role;
    private String opportunityType;
    private String industry;
    private String location;
    private String workMode;
    private String duration;
    private double stipend;
    private double salary;
    private String requiredSkills;
    private int applicationCount;
    private String deadline;

    public Company(
            String companyName,
            String role,
            String opportunityType,
            String industry,
            String location,
            String workMode,
            String duration,
            double stipend,
            double salary,
            String requiredSkills,
            int applicationCount,
            String deadline) {

        this.companyName = companyName;
        this.role = role;
        this.opportunityType = opportunityType;
        this.industry = industry;
        this.location = location;
        this.workMode = workMode;
        this.duration = duration;
        this.stipend = stipend;
        this.salary = salary;
        this.requiredSkills = requiredSkills;
        this.applicationCount = applicationCount;
        this.deadline = deadline;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getRole() {
        return role;
    }

    public String getOpportunityType() {
        return opportunityType;
    }

    public String getIndustry() {
        return industry;
    }

    public String getLocation() {
        return location;
    }

    public String getWorkMode() {
        return workMode;
    }

    public String getDuration() {
        return duration;
    }

    public double getStipend() {
        return stipend;
    }

    public double getSalary() {
        return salary;
    }

    public String getRequiredSkills() {
        return requiredSkills;
    }

    public int getApplicationCount() {
        return applicationCount;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setApplicationCount(int applicationCount) {
        this.applicationCount = applicationCount;
    }

    public boolean isInternship() {

        return opportunityType.equalsIgnoreCase(
                "Internship"
        );
    }

    public String getPayment() {

        if (isInternship()) {

            if (stipend > 0) {

                return "₹" + stipend + " / Month";

            } else {

                return "Unpaid";
            }
        }

        if (salary > 0) {

            return "₹" + salary + " LPA";
        }

        return "Not Specified";
    }

    public boolean requiresSkill(String skill) {

        String[] requiredSkillsArray =
                requiredSkills.split(",");

        for (String requiredSkill :
                requiredSkillsArray) {

            if (requiredSkill
                    .trim()
                    .equalsIgnoreCase(
                            skill.trim()
                    )) {

                return true;
            }
        }

        return false;
    }

    public int getMatchingSkillCount(
            ArrayList<String> studentSkills) {

        int count = 0;

        for (String skill : studentSkills) {

            if (requiresSkill(skill)) {

                count++;
            }
        }

        return count;
    }

    public static ArrayList<Company> getSampleOpportunities() {

        ArrayList<Company> opportunities =
                new ArrayList<>();

        opportunities.add(
                new Company(
                        "Google",
                        "Software Engineering Intern",
                        "Internship",
                        "Technology",
                        "Bangalore",
                        "Hybrid",
                        "3 Months",
                        30000,
                        0,
                        "Java, OOP, SQL",
                        124,
                        "15-10-2026"
                )
        );

        opportunities.add(
                new Company(
                        "Microsoft",
                        "AI/ML Intern",
                        "Internship",
                        "Technology",
                        "Hyderabad",
                        "Remote",
                        "6 Months",
                        35000,
                        0,
                        "Python, Machine Learning, SQL",
                        87,
                        "20-10-2026"
                )
        );

        opportunities.add(
                new Company(
                        "TCS",
                        "Software Developer",
                        "Full-Time",
                        "Information Technology",
                        "Bangalore",
                        "On-site",
                        "Permanent",
                        0,
                        6.5,
                        "Java, SQL, OOP",
                        342,
                        "30-10-2026"
                )
        );

        opportunities.add(
                new Company(
                        "Infosys",
                        "Data Analyst Intern",
                        "Internship",
                        "Information Technology",
                        "Pune",
                        "Hybrid",
                        "4 Months",
                        22000,
                        0,
                        "Python, SQL, Statistics",
                        96,
                        "25-10-2026"
                )
        );

        opportunities.add(
                new Company(
                        "Wipro",
                        "Python Developer",
                        "Full-Time",
                        "Information Technology",
                        "Hyderabad",
                        "Hybrid",
                        "Permanent",
                        0,
                        5.8,
                        "Python, SQL, OOP",
                        218,
                        "05-11-2026"
                )
        );

        opportunities.add(
                new Company(
                        "Deloitte",
                        "Cybersecurity Intern",
                        "Internship",
                        "Consulting",
                        "Bangalore",
                        "On-site",
                        "3 Months",
                        25000,
                        0,
                        "Cybersecurity, Networking, Python",
                        76,
                        "12-10-2026"
                )
        );

        return opportunities;
    }

    @Override
    public String toString() {

        return companyName
                + " - "
                + role
                + " - "
                + opportunityType;
    }
}