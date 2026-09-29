package ui;

import model.Application;
import model.Company;
import model.Student;
import service.ApplicationManager;
import service.ReportManager;
import service.SearchManager;
import util.ReminderThread;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DashboardFrame extends JFrame {

    private Student student;

    private ApplicationManager applicationManager;
    private SearchManager searchManager;
    private ReportManager reportManager;

    private ReminderThread reminderThread;

    private final Color NAVY =
            new Color(30, 58, 95);

    private final Color BLUE =
            new Color(52, 101, 164);

    private final Color LIGHT_BLUE =
            new Color(235, 243, 252);

    private final Color GREEN =
            new Color(52, 140, 92);

    private final Color ORANGE =
            new Color(218, 140, 45);

    private final Color RED =
            new Color(190, 70, 70);

    private final Color DARK_TEXT =
            new Color(40, 50, 60);

    public DashboardFrame(Student student) {

        this.student = student;

        applicationManager =
                new ApplicationManager();

        searchManager =
                new SearchManager();

        reportManager =
                new ReportManager();

        reminderThread =
                new ReminderThread(
                        applicationManager
                                .getApplications()
                );

        reminderThread.start();

        setTitle(
                "InternTrack - Student Dashboard"
        );

        setSize(900, 650);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        getContentPane().setBackground(
                LIGHT_BLUE
        );

        createInterface();

        setVisible(true);
    }

    private void createInterface() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout(
                                20,
                                20
                        )
                );

        mainPanel.setBackground(
                LIGHT_BLUE
        );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        30,
                        25,
                        30
                )
        );

        JPanel headingPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                1,
                                5,
                                5
                        )
                );

        headingPanel.setBackground(
                LIGHT_BLUE
        );

        JLabel title =
                new JLabel(
                        "INTERNTRACK",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        30
                )
        );

        title.setForeground(
                NAVY
        );

        JLabel welcome =
                new JLabel(
                        "Welcome, "
                        + student.getName()
                        + " | "
                        + student.getRegisterNumber(),
                        SwingConstants.CENTER
                );

        welcome.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        welcome.setForeground(
                DARK_TEXT
        );

        headingPanel.add(title);
        headingPanel.add(welcome);

        mainPanel.add(
                headingPanel,
                BorderLayout.NORTH
        );

        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(
                                3,
                                2,
                                18,
                                18
                        )
                );

        buttonPanel.setBackground(
                LIGHT_BLUE
        );

        JButton opportunitiesButton =
                createDashboardButton(
                        "Browse Opportunities",
                        BLUE
                );

        JButton applicationsButton =
                createDashboardButton(
                        "My Applications",
                        BLUE
                );

        JButton updatesButton =
                createDashboardButton(
                        "Application Updates",
                        ORANGE
                );

        JButton recommendationsButton =
                createDashboardButton(
                        "Recommended Roles",
                        GREEN
                );

        JButton interviewsButton =
                createDashboardButton(
                        "Interviews",
                        GREEN
                );

        JButton profileButton =
                createDashboardButton(
                        "My Profile",
                        NAVY
                );

        buttonPanel.add(
                opportunitiesButton
        );

        buttonPanel.add(
                applicationsButton
        );

        buttonPanel.add(
                updatesButton
        );

        buttonPanel.add(
                recommendationsButton
        );

        buttonPanel.add(
                interviewsButton
        );

        buttonPanel.add(
                profileButton
        );

        mainPanel.add(
                buttonPanel,
                BorderLayout.CENTER
        );

        JPanel bottomPanel =
                new JPanel();

        bottomPanel.setBackground(
                LIGHT_BLUE
        );

        JButton searchButton =
                createSmallButton(
                        "Search",
                        BLUE
                );

        JButton summaryButton =
                createSmallButton(
                        "Application Summary",
                        ORANGE
                );

        JButton logoutButton =
                createSmallButton(
                        "Logout",
                        RED
                );

        bottomPanel.add(searchButton);
        bottomPanel.add(summaryButton);
        bottomPanel.add(logoutButton);

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        opportunitiesButton.addActionListener(
                e -> openApplicationFrame()
        );

        applicationsButton.addActionListener(
                e -> showMyApplications()
        );

        updatesButton.addActionListener(
                e -> showApplicationUpdates()
        );

        recommendationsButton.addActionListener(
                e -> showRecommendedRoles()
        );

        interviewsButton.addActionListener(
                e -> openInterviewFrame()
        );

        profileButton.addActionListener(
                e -> openProfileFrame()
        );

        searchButton.addActionListener(
                e -> search()
        );

        summaryButton.addActionListener(
                e -> showSummary()
        );

        logoutButton.addActionListener(
                e -> logout()
        );

        add(mainPanel);
    }

    private JButton createDashboardButton(
            String text,
            Color color) {

        JButton button =
                new JButton(text);

        button.setBackground(color);
        button.setForeground(Color.WHITE);

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        button.setFocusPainted(false);
        button.setOpaque(true);

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        10,
                        15,
                        10
                )
        );

        return button;
    }

    private JButton createSmallButton(
            String text,
            Color color) {

        JButton button =
                new JButton(text);

        button.setBackground(color);
        button.setForeground(Color.WHITE);

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        button.setFocusPainted(false);
        button.setOpaque(true);

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        8,
                        15,
                        8,
                        15
                )
        );

        return button;
    }

    private void openApplicationFrame() {

        new ApplicationFrame(
                student,
                applicationManager
        );
    }

    private void openInterviewFrame() {

        new InterviewFrame();
    }

    private void openProfileFrame() {

        new ProfileFrame(
                student
        );
    }

    private void showMyApplications() {

        ArrayList<Application> applications =
                applicationManager
                        .getApplications();

        if (applications.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "You have not applied for any "
                    + "opportunities yet.\n\n"
                    + "Browse the available opportunities "
                    + "and select Apply Now.",
                    "My Applications",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }

        StringBuilder message =
                new StringBuilder();

        message.append(
                "MY APPLICATIONS\n\n"
        );

        for (
                Application application :
                applications
        ) {

            message.append(
                    "Company : "
                    + application.getCompanyName()
                    + "\n"
            );

            message.append(
                    "Role    : "
                    + application.getRole()
                    + "\n"
            );

            message.append(
                    "Status  : "
                    + application.getStatus()
                    + "\n"
            );

            message.append(
                    "Applied : "
                    + application.getApplicationDate()
                    + "\n"
            );

            message.append(
                    "--------------------------------\n"
            );
        }

        JTextArea area =
                new JTextArea(
                        message.toString()
                );

        area.setEditable(false);

        area.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        13
                )
        );

        JScrollPane scroll =
                new JScrollPane(area);

        scroll.setPreferredSize(
                new Dimension(
                        600,
                        400
                )
        );

        JOptionPane.showMessageDialog(
                this,
                scroll,
                "My Applications",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void showApplicationUpdates() {

        new NotificationFrame(
                student,
                applicationManager
        );
    }

    private void showRecommendedRoles() {

        ArrayList<Company> opportunities =
                Company.getSampleOpportunities();

        ArrayList<Application> applications =
                applicationManager
                        .getApplications();

        StringBuilder message =
                new StringBuilder();

        message.append(
                "RECOMMENDED ROLES FOR "
                + student.getName()
                + "\n\n"
        );

        int recommendationCount = 0;

        for (
                Company company :
                opportunities
        ) {

            boolean alreadyApplied = false;

            for (
                    Application application :
                    applications
            ) {

                if (
                        application
                                .getCompanyName()
                                .equalsIgnoreCase(
                                        company.getCompanyName()
                                )
                        &&
                        application
                                .getRole()
                                .equalsIgnoreCase(
                                        company.getRole()
                                )
                ) {

                    alreadyApplied = true;

                    break;
                }
            }

            int matches =
                    company.getMatchingSkillCount(
                            student.getSkills()
                    );

            if (
                    !alreadyApplied
                    &&
                    matches > 0
            ) {

                message.append(
                        "Company : "
                        + company.getCompanyName()
                        + "\n"
                );

                message.append(
                        "Role    : "
                        + company.getRole()
                        + "\n"
                );

                message.append(
                        "Type    : "
                        + company.getOpportunityType()
                        + "\n"
                );

                message.append(
                        "Location: "
                        + company.getLocation()
                        + "\n"
                );

                message.append(
                        "Payment : "
                        + company.getPayment()
                        + "\n"
                );

                message.append(
                        "Matching Skills: "
                        + matches
                        + "\n"
                );

                message.append(
                        "Required Skills: "
                        + company.getRequiredSkills()
                        + "\n"
                );

                message.append(
                        "Deadline: "
                        + company.getDeadline()
                        + "\n"
                );

                message.append(
                        "--------------------------------\n"
                );

                recommendationCount++;
            }
        }

        if (recommendationCount == 0) {

            message.append(
                    "No additional matching roles "
                    + "were found.\n\n"
                    + "Try adding more skills to "
                    + "your profile."
            );
        }

        JTextArea area =
                new JTextArea(
                        message.toString()
                );

        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        area.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        JScrollPane scroll =
                new JScrollPane(area);

        scroll.setPreferredSize(
                new Dimension(
                        650,
                        450
                )
        );

        JOptionPane.showMessageDialog(
                this,
                scroll,
                "Recommended Roles",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void search() {

        String keyword =
                JOptionPane.showInputDialog(
                        this,
                        "Search your applications by "
                        + "company, role or status:"
                );

        if (
                keyword == null
                ||
                keyword.trim().isEmpty()
        ) {

            return;
        }

        ArrayList<Application> results =
                searchManager.search(
                        applicationManager
                                .getApplications(),
                        keyword
                );

        if (results.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No matching applications found.",
                    "Search",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }

        StringBuilder message =
                new StringBuilder();

        message.append(
                "SEARCH RESULTS\n\n"
        );

        for (
                Application application :
                results
        ) {

            message.append(
                    application.getCompanyName()
                    + " - "
                    + application.getRole()
                    + "\n"
                    + "Status: "
                    + application.getStatus()
                    + "\n\n"
            );
        }

        JTextArea area =
                new JTextArea(
                        message.toString()
                );

        area.setEditable(false);

        area.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        JOptionPane.showMessageDialog(
                this,
                new JScrollPane(area),
                "Search Results",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void showSummary() {

        String summary =
                reportManager.getSummary(
                        applicationManager
                                .getApplications()
                );

        JTextArea area =
                new JTextArea(
                        summary
                );

        area.setEditable(false);

        area.setFont(
                new Font(
                        "Monospaced",
                        Font.BOLD,
                        14
                )
        );

        JOptionPane.showMessageDialog(
                this,
                area,
                "Application Summary",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void logout() {

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to logout?",
                        "Logout",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                choice ==
                JOptionPane.YES_OPTION
        ) {

            reminderThread.stopReminder();

            dispose();

            new LoginFrame();
        }
    }
}