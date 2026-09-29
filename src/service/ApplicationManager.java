package service;

import exceptions.DuplicateApplicationException;
import exceptions.InvalidApplicationException;
import model.Application;
import util.FileManager;

import java.io.IOException;
import java.util.ArrayList;

public class ApplicationManager {

    private ArrayList<Application> applications;

    public ApplicationManager() {

        applications = new ArrayList<>();

        try {
            applications = FileManager.loadApplications();

        } catch (IOException e) {

            System.out.println(
                    "Unable to load saved applications."
            );
        }
    }

    public void addApplication(Application application)
            throws DuplicateApplicationException,
                   InvalidApplicationException {

        if (application == null) {

            throw new InvalidApplicationException(
                    "Application cannot be empty."
            );
        }

        if (application.getCompanyName() == null ||
            application.getCompanyName().trim().isEmpty()) {

            throw new InvalidApplicationException(
                    "Company name is required."
            );
        }

        if (application.getRole() == null ||
            application.getRole().trim().isEmpty()) {

            throw new InvalidApplicationException(
                    "Job role is required."
            );
        }

        for (Application existing : applications) {

            if (existing.getCompanyName()
                    .equalsIgnoreCase(
                            application.getCompanyName())
                && existing.getRole()
                    .equalsIgnoreCase(
                            application.getRole())) {

                throw new DuplicateApplicationException(
                        "This application already exists."
                );
            }
        }

        applications.add(application);

        save();
    }

    public void updateApplicationStatus(
            int applicationId,
            String status)
            throws InvalidApplicationException {

        if (status == null ||
            status.trim().isEmpty()) {

            throw new InvalidApplicationException(
                    "Status cannot be empty."
            );
        }

        for (Application application : applications) {

            if (application.getApplicationId()
                    == applicationId) {

                application.updateStatus(status);

                save();

                return;
            }
        }

        throw new InvalidApplicationException(
                "Application ID not found."
        );
    }

    public void removeApplication(int applicationId)
            throws InvalidApplicationException {

        for (Application application : applications) {

            if (application.getApplicationId()
                    == applicationId) {

                applications.remove(application);

                save();

                return;
            }
        }

        throw new InvalidApplicationException(
                "Application ID not found."
        );
    }

    public ArrayList<Application> getApplications() {

        return applications;
    }

    public int getApplicationCount() {

        return applications.size();
    }

    private void save() {

        try {

            FileManager.saveApplications(
                    applications
            );

        } catch (IOException e) {

            System.out.println(
                    "Unable to save applications."
            );
        }
    }
}