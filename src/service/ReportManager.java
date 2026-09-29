package service;

import model.Application;

import java.util.ArrayList;

public class ReportManager {

    public int getTotalApplications(
            ArrayList<Application> applications) {

        return applications.size();
    }

    public int getInternshipCount(
            ArrayList<Application> applications) {

        int count = 0;

        for (Application application : applications) {

            if (application.getClass().getSimpleName()
                    .equals("InternshipApplication")) {

                count++;
            }
        }

        return count;
    }

    public int getFullTimeCount(
            ArrayList<Application> applications) {

        int count = 0;

        for (Application application : applications) {

            if (application.getClass().getSimpleName()
                    .equals("FullTimeApplication")) {

                count++;
            }
        }

        return count;
    }

    public int getStatusCount(
            ArrayList<Application> applications,
            String status) {

        int count = 0;

        for (Application application : applications) {

            if (application.getStatus()
                    .equalsIgnoreCase(status)) {

                count++;
            }
        }

        return count;
    }

    public String getSummary(
            ArrayList<Application> applications) {

        int total = getTotalApplications(applications);
        int internships = getInternshipCount(applications);
        int fullTime = getFullTimeCount(applications);

        int applied =
                getStatusCount(applications, "Applied");

        int assessment =
                getStatusCount(applications, "Assessment");

        int shortlisted =
                getStatusCount(applications, "Shortlisted");

        int interview =
                getStatusCount(applications, "Interview");

        int selected =
                getStatusCount(applications, "Selected");

        int rejected =
                getStatusCount(applications, "Rejected");

        return
                "APPLICATION SUMMARY\n\n" +
                "Total Applications : " + total + "\n" +
                "Internships        : " + internships + "\n" +
                "Full-Time Jobs     : " + fullTime + "\n\n" +
                "Applied            : " + applied + "\n" +
                "Assessment         : " + assessment + "\n" +
                "Shortlisted        : " + shortlisted + "\n" +
                "Interview          : " + interview + "\n" +
                "Selected           : " + selected + "\n" +
                "Rejected           : " + rejected;
    }
}