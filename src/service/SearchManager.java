package service;

import interfaces.Searchable;
import model.Application;

import java.util.ArrayList;

public class SearchManager {

    public ArrayList<Application> search(
            ArrayList<Application> applications,
            String keyword) {

        ArrayList<Application> results =
                new ArrayList<>();

        if (keyword == null ||
            keyword.trim().isEmpty()) {

            return results;
        }

        for (Application application : applications) {

            Searchable searchable = application;

            if (searchable.matches(keyword)) {
                results.add(application);
            }
        }

        return results;
    }

    public ArrayList<Application> searchByStatus(
            ArrayList<Application> applications,
            String status) {

        ArrayList<Application> results =
                new ArrayList<>();

        if (status == null ||
            status.trim().isEmpty()) {

            return results;
        }

        for (Application application : applications) {

            if (application.getStatus()
                    .equalsIgnoreCase(status)) {

                results.add(application);
            }
        }

        return results;
    }
}