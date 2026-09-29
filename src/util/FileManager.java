package util;

import model.Application;
import model.FullTimeApplication;
import model.InternshipApplication;

import java.io.*;
import java.util.ArrayList;

public class FileManager {

    private static final String FILE_NAME =
            "applications.txt";

    public static void saveApplications(
            ArrayList<Application> applications)
            throws IOException {

        FileWriter writer =
                new FileWriter(FILE_NAME);

        for (Application application : applications) {

            if (application instanceof InternshipApplication) {

                InternshipApplication internship =
                        (InternshipApplication) application;

                writer.write(
                        "INTERNSHIP|" +
                        application.getCompanyName() + "|" +
                        application.getRole() + "|" +
                        application.getLocation() + "|" +
                        application.getWorkMode() + "|" +
                        application.getApplicationDate() + "|" +
                        application.getStatus() + "|" +
                        application.getNotes() + "|" +
                        internship.getDurationMonths() + "|" +
                        internship.getStipend() +
                        "\n"
                );

            } else if (application instanceof FullTimeApplication) {

                FullTimeApplication fullTime =
                        (FullTimeApplication) application;

                writer.write(
                        "FULLTIME|" +
                        application.getCompanyName() + "|" +
                        application.getRole() + "|" +
                        application.getLocation() + "|" +
                        application.getWorkMode() + "|" +
                        application.getApplicationDate() + "|" +
                        application.getStatus() + "|" +
                        application.getNotes() + "|" +
                        fullTime.getSalary() + "|" +
                        fullTime.getEmploymentType() +
                        "\n"
                );
            }
        }

        writer.close();
    }

    public static ArrayList<Application> loadApplications()
            throws IOException {

        ArrayList<Application> applications =
                new ArrayList<>();

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return applications;
        }

        BufferedReader reader =
                new BufferedReader(
                        new FileReader(file)
                );

        String line;

        while ((line = reader.readLine()) != null) {

            String[] data = line.split("\\|");

            if (data.length < 10) {
                continue;
            }

            String type = data[0];
            String companyName = data[1];
            String role = data[2];
            String location = data[3];
            String workMode = data[4];
            String applicationDate = data[5];
            String status = data[6];
            String notes = data[7];

            Application application;

            if (type.equals("INTERNSHIP")) {

                int duration =
                        Integer.parseInt(data[8]);

                double stipend =
                        Double.parseDouble(data[9]);

                application =
                        new InternshipApplication(
                                companyName,
                                role,
                                location,
                                workMode,
                                applicationDate,
                                notes,
                                duration,
                                stipend
                        );

            } else {

                double salary =
                        Double.parseDouble(data[8]);

                String employmentType = data[9];

                application =
                        new FullTimeApplication(
                                companyName,
                                role,
                                location,
                                workMode,
                                applicationDate,
                                notes,
                                salary,
                                employmentType
                        );
            }

            application.updateStatus(status);

            applications.add(application);
        }

        reader.close();

        return applications;
    }
}