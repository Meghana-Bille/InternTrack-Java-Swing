package ui;

import exceptions.DuplicateApplicationException;
import exceptions.InvalidApplicationException;
import model.Application;
import model.Company;
import model.FullTimeApplication;
import model.InternshipApplication;
import model.Student;
import service.ApplicationManager;
import service.EligibilityManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ApplicationFrame extends JFrame {

    private Student student;
    private ApplicationManager applicationManager;
    private EligibilityManager eligibilityManager;

    private ArrayList<Company> opportunities;

    private JComboBox<Company> opportunityBox;
    private JTextArea detailsArea;

    private JTable applicationTable;
    private DefaultTableModel tableModel;

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

    private final Color DARK_TEXT =
            new Color(40, 50, 60);

    private final Color WHITE =
            Color.WHITE;

    public ApplicationFrame(
            Student student,
            ApplicationManager applicationManager) {

        this.student = student;
        this.applicationManager = applicationManager;

        eligibilityManager =
                new EligibilityManager();

        opportunities =
                new ArrayList<>();

        loadOpportunities();

        setTitle(
                "InternTrack - Career Opportunities"
        );

        setSize(1000, 720);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);

        getContentPane().setBackground(
                LIGHT_BLUE
        );

        createInterface();

        showOpportunityDetails();

        refreshApplications();

        setVisible(true);
    }

    private void loadOpportunities() {

        opportunities =
                Company.getSampleOpportunities();
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
                        "CAREER OPPORTUNITIES",
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
                        "Find internships and jobs that match your career goals",
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

        JTabbedPane tabs =
                new JTabbedPane();

        tabs.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        tabs.addTab(
                "Browse Opportunities",
                createOpportunityPanel()
        );

        tabs.addTab(
                "My Applications",
                createApplicationPanel()
        );

        mainPanel.add(
                tabs,
                BorderLayout.CENTER
        );

        add(mainPanel);
    }

    private JPanel createOpportunityPanel() {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                15,
                                15
                        )
                );

        panel.setBackground(
                LIGHT_BLUE
        );

        JPanel selectionPanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        selectionPanel.setBackground(
                WHITE
        );

        selectionPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                new Color(
                                        210,
                                        220,
                                        232
                                )
                        ),
                        new EmptyBorder(
                                12,
                                15,
                                12,
                                15
                        )
                )
        );

        JLabel selectLabel =
                new JLabel(
                        "Select Opportunity:"
                );

        selectLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        selectLabel.setForeground(
                NAVY
        );

        opportunityBox =
                new JComboBox<>();

        opportunityBox.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        for (
                Company company :
                opportunities
        ) {

            opportunityBox.addItem(
                    company
            );
        }

        selectionPanel.add(
                selectLabel,
                BorderLayout.WEST
        );

        selectionPanel.add(
                opportunityBox,
                BorderLayout.CENTER
        );

        panel.add(
                selectionPanel,
                BorderLayout.NORTH
        );

        detailsArea =
                new JTextArea();

        detailsArea.setEditable(false);
        detailsArea.setLineWrap(true);
        detailsArea.setWrapStyleWord(true);

        detailsArea.setBackground(
                WHITE
        );

        detailsArea.setForeground(
                DARK_TEXT
        );

        detailsArea.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        detailsArea.setBorder(
                new EmptyBorder(
                        15,
                        18,
                        15,
                        18
                )
        );

        JScrollPane detailsScroll =
                new JScrollPane(
                        detailsArea
                );

        detailsScroll.setBorder(
                BorderFactory.createTitledBorder(
                        new LineBorder(
                                new Color(
                                        210,
                                        220,
                                        232
                                )
                        ),
                        "Opportunity Details"
                )
        );

        panel.add(
                detailsScroll,
                BorderLayout.CENTER
        );

        JPanel bottomPanel =
                new JPanel();

        bottomPanel.setBackground(
                LIGHT_BLUE
        );

        JButton eligibilityButton =
                createButton(
                        "Check Eligibility",
                        ORANGE
                );

        JButton applyButton =
                createButton(
                        "Apply Now",
                        GREEN
                );

        JButton refreshButton =
                createButton(
                        "Refresh Opportunities",
                        BLUE
                );

        bottomPanel.add(
                eligibilityButton
        );

        bottomPanel.add(
                applyButton
        );

        bottomPanel.add(
                refreshButton
        );

        panel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        opportunityBox.addActionListener(
                e -> showOpportunityDetails()
        );

        eligibilityButton.addActionListener(
                e -> checkEligibility()
        );

        applyButton.addActionListener(
                e -> applyForOpportunity()
        );

        refreshButton.addActionListener(
                e -> refreshOpportunities()
        );

        return panel;
    }

    private JPanel createApplicationPanel() {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        panel.setBackground(
                LIGHT_BLUE
        );

        String[] columns = {
                "Application ID",
                "Company",
                "Role",
                "Status",
                "Applied On"
        };

        tableModel =
                new DefaultTableModel(
                        columns,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {

                        return false;
                    }
                };

        applicationTable =
                new JTable(
                        tableModel
                );

        applicationTable.setRowHeight(32);

        applicationTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        applicationTable.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                13
                        )
                );

        applicationTable.getTableHeader()
                .setBackground(
                        NAVY
                );

        applicationTable.getTableHeader()
                .setForeground(
                        WHITE
                );

        applicationTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        StatusRenderer renderer =
                new StatusRenderer();

        applicationTable
                .getColumnModel()
                .getColumn(3)
                .setCellRenderer(renderer);

        JScrollPane scrollPane =
                new JScrollPane(
                        applicationTable
                );

        scrollPane.setBorder(
                BorderFactory.createTitledBorder(
                        new LineBorder(
                                new Color(
                                        210,
                                        220,
                                        232
                                )
                        ),
                        "My Applications"
                )
        );

        panel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        JPanel buttonPanel =
                new JPanel();

        buttonPanel.setBackground(
                LIGHT_BLUE
        );

        JButton detailsButton =
                createButton(
                        "View Details",
                        NAVY
                );

        JButton refreshButton =
                createButton(
                        "Refresh Applications",
                        BLUE
                );

        buttonPanel.add(
                detailsButton
        );

        buttonPanel.add(
                refreshButton
        );

        panel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        refreshButton.addActionListener(
                e -> refreshApplications()
        );

        detailsButton.addActionListener(
                e -> showSelectedApplicationDetails()
        );

        return panel;
    }

    private JButton createButton(
            String text,
            Color color) {

        JButton button =
                new JButton(text);

        button.setBackground(color);
        button.setForeground(WHITE);

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
                        10,
                        18,
                        10,
                        18
                )
        );

        return button;
    }

    private void showOpportunityDetails() {

        Company company =
                (Company) opportunityBox
                        .getSelectedItem();

        if (company == null) {
            return;
        }

        StringBuilder details =
                new StringBuilder();

        details.append(
                "COMPANY\n"
        );
        details.append(
                company.getCompanyName()
        );

        details.append(
                "\n\nROLE\n"
        );
        details.append(
                company.getRole()
        );

        details.append(
                "\n\nTYPE\n"
        );
        details.append(
                company.getOpportunityType()
        );

        details.append(
                "\n\nINDUSTRY\n"
        );
        details.append(
                company.getIndustry()
        );

        details.append(
                "\n\nLOCATION\n"
        );
        details.append(
                company.getLocation()
        );

        details.append(
                "\n\nWORK MODE\n"
        );
        details.append(
                company.getWorkMode()
        );

        details.append(
                "\n\nDURATION / EMPLOYMENT\n"
        );
        details.append(
                company.getDuration()
        );

        details.append(
                "\n\nSTIPEND / SALARY\n"
        );
        details.append(
                company.getPayment()
        );

        details.append(
                "\n\nREQUIRED SKILLS\n"
        );
        details.append(
                company.getRequiredSkills()
        );

        details.append(
                "\n\nAPPLICATIONS RECEIVED\n"
        );
        details.append(
                company.getApplicationCount()
        );

        details.append(
                "\n\nAPPLICATION DEADLINE\n"
        );
        details.append(
                company.getDeadline()
        );

        detailsArea.setText(
                details.toString()
        );

        detailsArea.setCaretPosition(0);
    }

    private void checkEligibility() {

        Company company =
                (Company) opportunityBox
                        .getSelectedItem();

        if (company == null) {
            return;
        }

        String message =
                eligibilityManager
                        .getEligibilityMessage(
                                student,
                                company
                        );

        JTextArea area =
                new JTextArea(
                        message
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

        JScrollPane scrollPane =
                new JScrollPane(area);

        scrollPane.setPreferredSize(
                new Dimension(
                        500,
                        350
                )
        );

        JOptionPane.showMessageDialog(
                this,
                scrollPane,
                "Eligibility Check",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void applyForOpportunity() {

        Company company =
                (Company) opportunityBox
                        .getSelectedItem();

        if (company == null) {
            return;
        }

        boolean eligible =
                eligibilityManager.isEligible(
                        student,
                        company
                );

        if (!eligible) {

            JOptionPane.showMessageDialog(
                    this,
                    eligibilityManager
                            .getEligibilityMessage(
                                    student,
                                    company
                            )
                    + "\n\nPlease choose another role "
                    + "that matches your current skills.",
                    "Not Eligible",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        for (
                Application application :
                applicationManager
                        .getApplications()
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

                JOptionPane.showMessageDialog(
                        this,
                        "You have already applied for this role.",
                        "Already Applied",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }
        }

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern(
                        "dd-MM-yyyy"
                );

        String applicationDate =
                LocalDate.now()
                        .format(formatter);

        String notes =
                "Applied through InternTrack.";

        Application application;

        if (company.isInternship()) {

            application =
                    new InternshipApplication(
                            company.getCompanyName(),
                            company.getRole(),
                            company.getLocation(),
                            company.getWorkMode(),
                            applicationDate,
                            notes,
                            extractMonths(
                                    company.getDuration()
                            ),
                            company.getStipend()
                    );

        } else {

            application =
                    new FullTimeApplication(
                            company.getCompanyName(),
                            company.getRole(),
                            company.getLocation(),
                            company.getWorkMode(),
                            applicationDate,
                            notes,
                            company.getSalary(),
                            company.getDuration()
                    );
        }

        try {

            applicationManager.addApplication(
                    application
            );

            company.setApplicationCount(
                    company.getApplicationCount() + 1
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Application submitted successfully!\n\n"
                    + "Company: "
                    + company.getCompanyName()
                    + "\nRole: "
                    + company.getRole()
                    + "\n\n"
                    + "You can track the application "
                    + "from My Applications.",
                    "Application Submitted",
                    JOptionPane.INFORMATION_MESSAGE
            );

            showOpportunityDetails();
            refreshApplications();

        } catch (
                DuplicateApplicationException |
                InvalidApplicationException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Application Error",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private int extractMonths(
            String duration) {

        String number = "";

        for (
                int i = 0;
                i < duration.length();
                i++
        ) {

            char ch =
                    duration.charAt(i);

            if (Character.isDigit(ch)) {
                number += ch;
            }
        }

        if (number.isEmpty()) {
            return 1;
        }

        return Integer.parseInt(number);
    }

    private void refreshApplications() {

        tableModel.setRowCount(0);

        ArrayList<Application> applications =
                applicationManager
                        .getApplications();

        for (
                Application application :
                applications
        ) {

            tableModel.addRow(
                    new Object[]{
                            application.getApplicationId(),
                            application.getCompanyName(),
                            application.getRole(),
                            application.getStatus(),
                            application.getApplicationDate()
                    }
            );
        }
    }

    private void showSelectedApplicationDetails() {

        int selectedRow =
                applicationTable.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an application first.",
                    "No Application Selected",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int applicationId =
                Integer.parseInt(
                        tableModel
                                .getValueAt(
                                        selectedRow,
                                        0
                                )
                                .toString()
                );

        Application selectedApplication =
                null;

        for (
                Application application :
                applicationManager
                        .getApplications()
        ) {

            if (
                    application.getApplicationId()
                            == applicationId
            ) {

                selectedApplication =
                        application;

                break;
            }
        }

        if (selectedApplication == null) {
            return;
        }

        String message =
                "APPLICATION DETAILS\n\n"
                + "Application ID: "
                + selectedApplication
                        .getApplicationId()
                + "\n\n"
                + "Company: "
                + selectedApplication
                        .getCompanyName()
                + "\n\n"
                + "Role: "
                + selectedApplication
                        .getRole()
                + "\n\n"
                + "Location: "
                + selectedApplication
                        .getLocation()
                + "\n\n"
                + "Work Mode: "
                + selectedApplication
                        .getWorkMode()
                + "\n\n"
                + "Applied On: "
                + selectedApplication
                        .getApplicationDate()
                + "\n\n"
                + "Status: "
                + selectedApplication
                        .getStatus()
                + "\n\n"
                + "Notes: "
                + selectedApplication
                        .getNotes();

        JOptionPane.showMessageDialog(
                this,
                message,
                "Application Details",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void refreshOpportunities() {

        int selectedIndex =
                opportunityBox
                        .getSelectedIndex();

        opportunityBox.removeAllItems();

        for (
                Company company :
                opportunities
        ) {

            opportunityBox.addItem(
                    company
            );
        }

        if (
                selectedIndex >= 0
                &&
                selectedIndex
                        < opportunityBox.getItemCount()
        ) {

            opportunityBox.setSelectedIndex(
                    selectedIndex
            );
        }

        showOpportunityDetails();
    }

    private class StatusRenderer
            extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(
                JTable table,
                Object value,
                boolean isSelected,
                boolean hasFocus,
                int row,
                int column) {

            Component component =
                    super.getTableCellRendererComponent(
                            table,
                            value,
                            isSelected,
                            hasFocus,
                            row,
                            column
                    );

            String status =
                    value == null
                            ? ""
                            : value.toString();

            setHorizontalAlignment(
                    SwingConstants.CENTER
            );

            setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            12
                    )
            );

            if (!isSelected) {

                if (
                        status.equalsIgnoreCase(
                                "Selected"
                        )
                ) {

                    setBackground(
                            new Color(
                                    213,
                                    245,
                                    225
                            )
                    );

                    setForeground(
                            new Color(
                                    30,
                                    110,
                                    65
                            )
                    );

                } else if (
                        status.equalsIgnoreCase(
                                "Rejected"
                        )
                ) {

                    setBackground(
                            new Color(
                                    250,
                                    220,
                                    220
                            )
                    );

                    setForeground(
                            new Color(
                                    165,
                                    45,
                                    45
                            )
                    );

                } else if (
                        status.equalsIgnoreCase(
                                "Interview"
                        )
                ) {

                    setBackground(
                            new Color(
                                    255,
                                    235,
                                    195
                            )
                    );

                    setForeground(
                            new Color(
                                    165,
                                    105,
                                    25
                            )
                    );

                } else {

                    setBackground(
                            new Color(
                                    220,
                                    235,
                                    250
                            )
                    );

                    setForeground(
                            NAVY
                    );
                }

            } else {

                setBackground(
                        table.getSelectionBackground()
                );

                setForeground(
                        table.getSelectionForeground()
                );
            }

            return component;
        }
    }
}