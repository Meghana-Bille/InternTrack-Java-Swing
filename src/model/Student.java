package model;

import java.util.ArrayList;

public class Student {

    private String name;
    private String registerNumber;
    private String email;
    private String phone;

    private ArrayList<String> skills;

    public Student(
            String name,
            String registerNumber,
            String email,
            String phone) {

        this.name = name;
        this.registerNumber = registerNumber;
        this.email = email;
        this.phone = phone;

        skills = new ArrayList<>();

        // Default skills for the student profile.
        skills.add("Java");
        skills.add("Python");
        skills.add("SQL");
        skills.add("OOP");
        skills.add("DBMS");
    }

    public Student(
            String name,
            String registerNumber,
            String email,
            String phone,
            ArrayList<String> skills) {

        this.name = name;
        this.registerNumber = registerNumber;
        this.email = email;
        this.phone = phone;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public ArrayList<String> getSkills() {
        return skills;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSkills(ArrayList<String> skills) {
        this.skills = skills;
    }

    public void addSkill(String skill) {

        if (skill != null &&
            !skill.trim().isEmpty() &&
            !skills.contains(skill.trim())) {

            skills.add(skill.trim());
        }
    }

    public boolean hasSkill(String skill) {

        for (String studentSkill : skills) {

            if (studentSkill.equalsIgnoreCase(skill.trim())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {

        return registerNumber + " - " + name;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Student)) {
            return false;
        }

        Student student = (Student) obj;

        return registerNumber.equals(
                student.registerNumber
        );
    }

    @Override
    public int hashCode() {

        return registerNumber.hashCode();
    }
}