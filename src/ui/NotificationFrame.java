package ui;

import model.Application;
import model.Student;
import service.ApplicationManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class NotificationFrame extends JFrame {

    private Student student;
    private ApplicationManager applicationManager;

    private JPanel notificationPanel;

    private final Color NAVY =
            new Color(30, 58, 95);

    private final Color BLUE =
            new Color(52, 101, 164);

    private final Color LIGHT_BLUE =
            new Color(235, 243, 252);

    private final Color GREEN =
            new Color(52, 140, 92);

    private final Color RED =
            new Color(190, 70, 70);

    private final Color ORANGE =
            new Color(218, 140, 45);

    private final Color DARK_TEXT =
            new Color(40, 50, 60);

    private final Color WHITE =
            Color.WHITE;

    public NotificationFrame(
            Student student,
            ApplicationManager applicationManager) {

        this.student = student;
        this.applicationManager = applicationManager;

        setTitle(
                "InternTrack - Application Updates"
        );

        setSize(880, 680);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);

        getContentPane().setBackground(
                LIGHT_BLUE
        );

        createInterface();

        loadNotifications();

        setVisible(true);
    }

    private void createInterface() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout(
                                15,
                                15
                        )
                );

        mainPanel.setBackground(
                LIGHT_BLUE
        );

        mainPanel.setBorder(
                new EmptyBorder(
                        20,
                        25,
                        20,
                        25
                )
        );

        JPanel headerPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                1,
                                3,
                                3
                        )
                );

        headerPanel.setBackground(
                LIGHT_BLUE
        );

        JLabel title =
                new JLabel(
                        "APPLICATION UPDATES",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        27
                )
        );

        title.setForeground(
                NAVY
        );

        JLabel subtitle =
                new JLabel(
                        "Track the latest updates from your applications",
                        SwingConstants.CENTER
                );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        subtitle.setForeground(
                DARK_TEXT
        );

        headerPanel.add(title);
        headerPanel.add(subtitle);

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        notificationPanel =
                new JPanel();

        notificationPanel.setLayout(
                new BoxLayout(
                        notificationPanel,
                        BoxLayout.Y_AXIS
                )
        );

        notificationPanel.setBackground(
                LIGHT_BLUE
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        notificationPanel
                );

        scrollPane.setBorder(
                BorderFactory.createEmptyBorder()
        );

        scrollPane.getVerticalScrollBar()
                .setUnitIncrement(16);

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        JPanel bottomPanel =
                new JPanel();

        bottomPanel.setBackground(
                LIGHT_BLUE
        );

        JButton refreshButton =
                createButton(
                        "Refresh Updates",
                        BLUE
                );

        bottomPanel.add(
                refreshButton
        );

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        refreshButton.addActionListener(
                e -> loadNotifications()
        );

        add(mainPanel);
    }

    private void loadNotifications() {

        notificationPanel.removeAll();

        ArrayList<Application> applications =
                applicationManager
                        .getApplications();

        if (applications.isEmpty()) {

            notificationPanel.add(
                    createEmptyPanel()
            );

        } else {

            for (
                    Application application :
                    applications
            ) {

                simulateCompanyResponse(
                        application
                );

                JPanel card =
                        createNotificationCard(
                                application
                        );

                notificationPanel.add(
                        card
                );

                notificationPanel.add(
                        Box.createVerticalStrut(
                                12
                        )
                );
            }
        }

        notificationPanel.revalidate();
        notificationPanel.repaint();
    }

    private JPanel createNotificationCard(
            Application application) {

        String status =
                application.getStatus();

        Color statusColor =
                getStatusColor(status);

        JPanel card =
                new JPanel(
                        new BorderLayout(
                                12,
                                12
                        )
                );

        card.setBackground(
                WHITE
        );

        card.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        220
                )
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                statusColor,
                                2
                        ),
                        new EmptyBorder(
                                15,
                                18,
                                15,
                                18
                        )
                )
        );

        JPanel heading =
                new JPanel(
                        new GridLayout(
                                2,
                                1,
                                2,
                                2
                        )
                );

        heading.setBackground(
                WHITE
        );

        JLabel companyLabel =
                new JLabel(
                        application.getCompanyName()
                );

        companyLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        19
                )
        );

        companyLabel.setForeground(
                NAVY
        );

        JLabel roleLabel =
                new JLabel(
                        application.getRole()
                );

        roleLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        roleLabel.setForeground(
                DARK_TEXT
        );

        heading.add(companyLabel);
        heading.add(roleLabel);

        card.add(
                heading,
                BorderLayout.NORTH
        );

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(
                                12,
                                5
                        )
                );

        centerPanel.setBackground(
                WHITE
        );

        JLabel statusLabel =
                new JLabel(
                        status.toUpperCase(),
                        SwingConstants.CENTER
                );

        statusLabel.setOpaque(true);

        statusLabel.setBackground(
                statusColor
        );

        statusLabel.setForeground(
                WHITE
        );

        statusLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );

        statusLabel.setBorder(
                BorderFactory.createEmptyBorder(
                        9,
                        14,
                        9,
                        14
                )
        );

        centerPanel.add(
                statusLabel,
                BorderLayout.WEST
        );

        JTextArea messageArea =
                new JTextArea(
                        createMessage(
                                application
                        )
                );

        messageArea.setEditable(false);

        messageArea.setLineWrap(true);

        messageArea.setWrapStyleWord(true);

        messageArea.setBackground(
                WHITE
        );

        messageArea.setForeground(
                DARK_TEXT
        );

        messageArea.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        centerPanel.add(
                messageArea,
                BorderLayout.CENTER
        );

        card.add(
                centerPanel,
                BorderLayout.CENTER
        );

        JPanel bottomPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );

        bottomPanel.setBackground(
                WHITE
        );

        if (
                status.equalsIgnoreCase(
                        "Selected"
                )
        ) {

            JLabel nextStep =
                    new JLabel(
                            "Next step: Check your interview details."
                    );

            nextStep.setForeground(
                    GREEN
            );

            bottomPanel.add(
                    nextStep
            );

        } else if (
                status.equalsIgnoreCase(
                        "Rejected"
                )
        ) {

            JLabel nextStep =
                    new JLabel(
                            "Explore Recommended Roles."
                    );

            nextStep.setForeground(
                    BLUE
            );

            bottomPanel.add(
                    nextStep
            );

        } else if (
                status.equalsIgnoreCase(
                        "Interview"
                )
        ) {

            JLabel nextStep =
                    new JLabel(
                            "Next step: Prepare for your interview."
                    );

            nextStep.setForeground(
                    ORANGE
            );

            bottomPanel.add(
                    nextStep
            );

        } else {

            JLabel nextStep =
                    new JLabel(
                            "Your application is currently under review."
                    );

            nextStep.setForeground(
                    BLUE
            );

            bottomPanel.add(
                    nextStep
            );
        }

        card.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        return card;
    }

    private JPanel createEmptyPanel() {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setBackground(
                WHITE
        );

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                new Color(
                                        210,
                                        220,
                                        232
                                )
                        ),
                        new EmptyBorder(
                                50,
                                30,
                                50,
                                30
                        )
                )
        );

        JLabel label =
                new JLabel(
                        "No application updates yet.",
                        SwingConstants.CENTER
                );

        label.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        label.setForeground(
                DARK_TEXT
        );

        panel.add(
                label,
                BorderLayout.CENTER
        );

        return panel;
    }

    private Color getStatusColor(
            String status) {

        if (
                status.equalsIgnoreCase(
                        "Selected"
                )
        ) {

            return GREEN;
        }

        if (
                status.equalsIgnoreCase(
                        "Rejected"
                )
        ) {

            return RED;
        }

        if (
                status.equalsIgnoreCase(
                        "Interview"
                )
        ) {

            return ORANGE;
        }

        return BLUE;
    }

    private String createMessage(
            Application application) {

        String status =
                application.getStatus();

        String company =
                application.getCompanyName();

        if (
                status.equalsIgnoreCase(
                        "Selected"
                )
        ) {

            return
                    "Congratulations, "
                    + student.getName()
                    + "!\n\n"
                    + company
                    + " has selected your application "
                    + "for the next stage of recruitment.\n\n"
                    + "Please check your interview schedule "
                    + "for the next steps.";

        }

        if (
                status.equalsIgnoreCase(
                        "Rejected"
                )
        ) {

            return
                    "Thank you for applying to "
                    + company
                    + ".\n\n"
                    + "Your application was not selected "
                    + "for this role.\n\n"
                    + "You can explore other roles that "
                    + "match your skills.";

        }

        if (
                status.equalsIgnoreCase(
                        "Interview"
                )
        ) {

            return
                    "Good news!\n\n"
                    + "Your application has moved to "
                    + "the interview stage.\n\n"
                    + "Please review your interview details "
                    + "and prepare accordingly.";

        }

        return
                "Your application has been received "
                + "by the company.\n\n"
                + "It is currently under review. "
                + "You will receive an update when "
                + "the status changes.";
    }

    private void simulateCompanyResponse(
            Application application) {

        String company =
                application.getCompanyName();

        if (
                company.equalsIgnoreCase(
                        "Google"
                )
        ) {

            application.updateStatus(
                    "Selected"
            );

        } else if (
                company.equalsIgnoreCase(
                        "Microsoft"
                )
        ) {

            application.updateStatus(
                    "Rejected"
            );

        } else if (
                company.equalsIgnoreCase(
                        "TCS"
                )
        ) {

            application.updateStatus(
                    "Interview"
            );
        }
    }

    private JButton createButton(
            String text,
            Color color) {

        JButton button =
                new JButton(text);

        button.setBackground(
                color
        );

        button.setForeground(
                WHITE
        );

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
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
}