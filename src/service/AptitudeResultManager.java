package service;

import model.AptitudeResult;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class AptitudeResultManager {

    public static void saveResult(
            AptitudeResult result,
            String email)
            throws IOException {

        if (result == null) {
            throw new IOException("Aptitude result cannot be null.");
        }

        if (email == null || email.trim().isEmpty()) {
            throw new IOException("Student email is required.");
        }

        ArrayList<AptitudeResult> results = loadResults(email);

        boolean updated = false;

        for (int i = 0; i < results.size(); i++) {

            AptitudeResult existing = results.get(i);

            if (existing == null) {
                continue;
            }

            if (existing.getApplicationId() == result.getApplicationId()) {

                results.set(i, result);
                updated = true;
                break;
            }
        }

        if (!updated) {
            results.add(result);
        }

        File file = new File(getFileName(email));

        try (
                PrintWriter writer =
                        new PrintWriter(
                                new FileWriter(file)
                        )
        ) {

            for (AptitudeResult aptitudeResult : results) {

                if (aptitudeResult == null) {
                    continue;
                }

                writer.println(
                        aptitudeResult.getApplicationId()
                                + "|"
                                + safeValue(
                                        aptitudeResult.getCompanyName()
                                )
                                + "|"
                                + safeValue(
                                        aptitudeResult.getRole()
                                )
                                + "|"
                                + aptitudeResult.getScore()
                                + "|"
                                + aptitudeResult.getCorrectAnswers()
                                + "|"
                                + aptitudeResult.getTotalQuestions()
                                + "|"
                                + aptitudeResult.getPercentage()
                                + "|"
                                + aptitudeResult.hasPassed()
                                + "|"
                                + safeValue(
                                        aptitudeResult.getCompletedDate()
                                )
                );
            }
        }
    }

    public static ArrayList<AptitudeResult> loadResults(
            String email)
            throws IOException {

        ArrayList<AptitudeResult> results =
                new ArrayList<>();

        if (email == null || email.trim().isEmpty()) {
            return results;
        }

        File file = new File(getFileName(email));

        if (!file.exists()) {
            return results;
        }

        try (
                BufferedReader reader =
                        new BufferedReader(
                                new FileReader(file)
                        )
        ) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\|", -1);

                if (parts.length < 9) {
                    System.err.println(
                            "Skipping malformed aptitude result record."
                    );
                    continue;
                }

                try {

                    int applicationId =
                            Integer.parseInt(parts[0]);

                    String companyName = parts[1];
                    String role = parts[2];

                    int score =
                            Integer.parseInt(parts[3]);

                    int correctAnswers =
                            Integer.parseInt(parts[4]);

                    int totalQuestions =
                            Integer.parseInt(parts[5]);

                    int percentage =
                            Integer.parseInt(parts[6]);

                    boolean passed =
                            Boolean.parseBoolean(parts[7]);

                    String completedDate = parts[8];

                    results.add(
                            new AptitudeResult(
                                    applicationId,
                                    companyName,
                                    role,
                                    score,
                                    correctAnswers,
                                    totalQuestions,
                                    percentage,
                                    passed,
                                    completedDate
                            )
                    );

                } catch (NumberFormatException ex) {

                    System.err.println(
                            "Skipping invalid aptitude result record."
                    );
                }
            }
        }

        return results;
    }

    public static AptitudeResult getResultForApplication(
            String email,
            int applicationId)
            throws IOException {

        ArrayList<AptitudeResult> results =
                loadResults(email);

        for (AptitudeResult result : results) {

            if (result == null) {
                continue;
            }

            if (result.getApplicationId() == applicationId) {
                return result;
            }
        }

        return null;
    }

    public static boolean hasResultForApplication(
            String email,
            int applicationId)
            throws IOException {

        return getResultForApplication(
                email,
                applicationId
        ) != null;
    }

    private static String getFileName(String email) {

        return "aptitude_results_"
                + sanitizeEmail(email)
                + ".txt";
    }

    private static String sanitizeEmail(String email) {

        if (email == null || email.trim().isEmpty()) {
            return "unknown_user";
        }

        String sanitized =
                email.trim()
                        .toLowerCase()
                        .replaceAll(
                                "[^a-zA-Z0-9_-]",
                                "_"
                        );

        if (sanitized.isEmpty()) {
            return "unknown_user";
        }

        return sanitized;
    }

    private static String safeValue(String value) {

        if (value == null) {
            return "";
        }

        return value
                .replace("|", "/")
                .replace("\n", " ")
                .replace("\r", " ");
    }
}