package service;

import model.Company;
import model.Student;

public class EligibilityManager {

    public boolean isEligible(
            Student student,
            Company company) {

        if (student == null || company == null) {
            return false;
        }

        String[] requiredSkills =
                company.getRequiredSkills().split(",");

        for (String requiredSkill :
                requiredSkills) {

            String skill =
                    requiredSkill.trim();

            if (!student.hasSkill(skill)) {

                return false;
            }
        }

        return true;
    }

    public String getEligibilityMessage(
            Student student,
            Company company) {

        if (student == null || company == null) {

            return "Unable to check eligibility.";
        }

        String type =
                company.getOpportunityType();

        String opportunityWord;

        switch (type.toLowerCase()) {

            case "internship":
                opportunityWord = "internship";
                break;

            case "full-time":
                opportunityWord = "full-time position";
                break;

            default:
                opportunityWord = "opportunity";
        }

        String[] requiredSkills =
                company.getRequiredSkills().split(",");

        StringBuilder message =
                new StringBuilder();

        message.append(
                "ELIGIBILITY CHECK\n\n"
        );

        message.append(
                "Company: "
                + company.getCompanyName()
                + "\n"
        );

        message.append(
                "Role: "
                + company.getRole()
                + "\n"
        );

        message.append(
                "Type: "
                + opportunityWord
                + "\n\n"
        );

        message.append(
                "Required Skills:\n"
        );

        boolean eligible = true;

        for (String requiredSkill :
                requiredSkills) {

            String skill =
                    requiredSkill.trim();

            if (student.hasSkill(skill)) {

                message.append(
                        "✓ "
                        + skill
                        + " - Available\n"
                );

            } else {

                message.append(
                        "✗ "
                        + skill
                        + " - Missing\n"
                );

                eligible = false;
            }
        }

        message.append("\n");

        if (eligible) {

            message.append(
                    "RESULT: ELIGIBLE\n\n"
            );

            message.append(
                    "You meet the listed skill "
                    + "requirements for this role.\n"
            );

        } else {

            message.append(
                    "RESULT: NOT ELIGIBLE\n\n"
            );

            message.append(
                    "Add the missing skills to your "
                    + "profile and explore similar roles "
                    + "after developing those skills.\n"
            );
        }

        return message.toString();
    }
}
