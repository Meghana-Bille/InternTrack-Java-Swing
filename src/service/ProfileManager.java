package service;

import model.Student;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ProfileManager {

    private static final String PROFILE_DIRECTORY =
            "profiles";

    private static final String PROFILE_HEADER =
            "INTERNTRACK_PROFILE_V2";

    public static void saveProfile(
            Student student)
            throws IOException {

        if (student == null) {

            throw new IOException(
                    "Student profile cannot be null."
            );
        }

        if (
                student.getEmail() == null
                ||
                student.getEmail()
                        .trim()
                        .isEmpty()
        ) {

            throw new IOException(
                    "Email is required for the student profile."
            );
        }

        File directory =
                new File(
                        PROFILE_DIRECTORY
                );

        if (!directory.exists()) {

            directory.mkdirs();
        }

        File profileFile =
                new File(
                        directory,
                        getProfileFileName(
                                student.getEmail()
                        )
                );

        try (
                PrintWriter writer =
                        new PrintWriter(
                                new FileWriter(
                                        profileFile
                                )
                        )
        ) {

            writer.println(
                    PROFILE_HEADER
            );

            writer.println(
                    safeValue(
                            student.getName()
                    )
            );

            writer.println(
                    safeValue(
                            student.getEmail()
                    )
            );

            writer.println(
                    safeValue(
                            student.getPhone()
                    )
            );

            writer.println(
                    safeValue(
                            student.getDepartment()
                    )
            );

            writer.println(
                    safeValue(
                            student.getYear()
                    )
            );

            writer.println(
                    student.getCgpa()
            );

            ArrayList<String> skills =
                    student.getSkills();

            writer.println(
                    skills.size()
            );

            for (
                    String skill :
                    skills
            ) {

                writer.println(
                        safeValue(
                                skill
                        )
                );
            }
        }
    }

    public static Student loadProfile(
            String email)
            throws IOException {

        if (
                email == null
                ||
                email.trim().isEmpty()
        ) {

            return null;
        }

        File profileFile =
                getProfileFileByEmail(
                        email
                );

        if (profileFile == null) {

            return null;
        }

        return readProfileFile(
                profileFile
        );
    }

    public static boolean profileExists(
            String email)
            throws IOException {

        if (
                email == null
                ||
                email.trim().isEmpty()
        ) {

            return false;
        }

        File profileFile =
                getProfileFileByEmail(
                        email
                );

        return profileFile != null
                &&
                profileFile.exists();
    }

    private static File getProfileFileByEmail(
            String email)
            throws IOException {

        File directory =
                new File(
                        PROFILE_DIRECTORY
                );

        if (
                !directory.exists()
                ||
                !directory.isDirectory()
        ) {

            return null;
        }

        File newProfileFile =
                new File(
                        directory,
                        getProfileFileName(
                                email
                        )
                );

        if (newProfileFile.exists()) {

            return newProfileFile;
        }

        File[] files =
                directory.listFiles();

        if (files == null) {

            return null;
        }

        String targetEmail =
                email
                        .trim()
                        .toLowerCase();

        for (
                File file :
                files
        ) {

            if (
                    !file.isFile()
                    ||
                    !file.getName()
                            .startsWith(
                                    "profile_"
                            )
                    ||
                    !file.getName()
                            .endsWith(
                                    ".txt"
                            )
            ) {

                continue;
            }

            String storedEmail =
                    findEmailInProfileFile(
                            file
                    );

            if (
                    storedEmail != null
                    &&
                    storedEmail
                            .trim()
                            .equalsIgnoreCase(
                                    targetEmail
                            )
            ) {

                return file;
            }
        }

        return null;
    }

    private static String
    findEmailInProfileFile(
            File file)
            throws IOException {

        try (
                BufferedReader reader =
                        new BufferedReader(
                                new FileReader(
                                        file
                                )
                        )
        ) {

            String firstLine =
                    reader.readLine();

            if (
                    PROFILE_HEADER.equals(
                            firstLine
                    )
            ) {

                reader.readLine();

                return reader.readLine();
            }

            String secondLine =
                    reader.readLine();

            if (secondLine == null) {

                return null;
            }

            return reader.readLine();
        }
    }

    private static Student readProfileFile(
            File profileFile)
            throws IOException {

        try (
                BufferedReader reader =
                        new BufferedReader(
                                new FileReader(
                                        profileFile
                                )
                        )
        ) {

            String firstLine =
                    reader.readLine();

            if (firstLine == null) {

                throw new IOException(
                        "Profile file is empty."
                );
            }

            if (
                    PROFILE_HEADER.equals(
                            firstLine
                    )
            ) {

                return readNewProfile(
                        reader
                );
            }

            return readOldProfile(
                    firstLine,
                    reader
            );
        }
    }

    private static Student readNewProfile(
            BufferedReader reader)
            throws IOException {

        String name =
                reader.readLine();

        String email =
                reader.readLine();

        String phone =
                reader.readLine();

        String department =
                reader.readLine();

        String year =
                reader.readLine();

        String cgpaLine =
                reader.readLine();

        String skillCountLine =
                reader.readLine();

        if (
                name == null
                ||
                email == null
                ||
                phone == null
                ||
                department == null
                ||
                year == null
                ||
                cgpaLine == null
                ||
                skillCountLine == null
        ) {

            throw new IOException(
                    "Profile file is incomplete."
            );
        }

        double cgpa;

        try {

            cgpa =
                    Double.parseDouble(
                            cgpaLine
                    );

        } catch (
                NumberFormatException ex
        ) {

            throw new IOException(
                    "Invalid CGPA stored in profile."
            );
        }

        int skillCount;

        try {

            skillCount =
                    Integer.parseInt(
                            skillCountLine
                    );

        } catch (
                NumberFormatException ex
        ) {

            throw new IOException(
                    "Invalid skill data in profile."
            );
        }

        ArrayList<String> skills =
                readSkills(
                        reader,
                        skillCount
                );

        return new Student(
                name,
                "",
                email,
                phone,
                department,
                year,
                cgpa,
                skills
        );
    }

    private static Student readOldProfile(
            String name,
            BufferedReader reader)
            throws IOException {

        String registerNumber =
                reader.readLine();

        String email =
                reader.readLine();

        String phone =
                reader.readLine();

        String department =
                reader.readLine();

        String year =
                reader.readLine();

        String cgpaLine =
                reader.readLine();

        String skillCountLine =
                reader.readLine();

        if (
                registerNumber == null
                ||
                email == null
                ||
                phone == null
                ||
                department == null
                ||
                year == null
                ||
                cgpaLine == null
                ||
                skillCountLine == null
        ) {

            throw new IOException(
                    "Old profile file is incomplete."
            );
        }

        double cgpa;

        try {

            cgpa =
                    Double.parseDouble(
                            cgpaLine
                    );

        } catch (
                NumberFormatException ex
        ) {

            throw new IOException(
                    "Invalid CGPA stored in profile."
            );
        }

        int skillCount;

        try {

            skillCount =
                    Integer.parseInt(
                            skillCountLine
                    );

        } catch (
                NumberFormatException ex
        ) {

            throw new IOException(
                    "Invalid skill data in profile."
            );
        }

        ArrayList<String> skills =
                readSkills(
                        reader,
                        skillCount
                );

        return new Student(
                name,
                registerNumber,
                email,
                phone,
                department,
                year,
                cgpa,
                skills
        );
    }

    private static ArrayList<String> readSkills(
            BufferedReader reader,
            int skillCount)
            throws IOException {

        ArrayList<String> skills =
                new ArrayList<>();

        for (
                int i = 0;
                i < skillCount;
                i++
        ) {

            String skill =
                    reader.readLine();

            if (skill != null) {

                skills.add(
                        skill
                );
            }
        }

        return skills;
    }

    private static String getProfileFileName(
            String email) {

        return
                "profile_"
                +
                sanitizeEmail(
                        email
                )
                +
                ".txt";
    }

    private static String sanitizeEmail(
            String email) {

        return email
                .trim()
                .toLowerCase()
                .replaceAll(
                        "[^a-zA-Z0-9_-]",
                        "_"
                );
    }

    private static String safeValue(
            String value) {

        if (value == null) {

            return "";
        }

        return value
                .replace(
                        "\n",
                        " "
                )
                .replace(
                        "\r",
                        " "
                );
    }
}