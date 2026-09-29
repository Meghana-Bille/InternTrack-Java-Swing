package ui;

import model.Interview;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class InterviewFrame extends JFrame {

    private ArrayList<Interview> interviews;

    private JTextField companyField;
    private JTextField roleField;
    private JTextField dateField;
    private JTextField roundField;

    private JComboBox<String> modeBox;
    private JComboBox<String> resultBox;

    private JTextArea notesArea;
    private JTextArea interviewArea;

    private final Color NAVY =
            new Color(30, 58, 95);

    private final Color BLUE =
            new Color(52, 101, 164);

    private final Color LIGHT_BLUE =
            new Color(235, 243, 252);

    private final Color GREEN =
            new Color(52, 140, 92);

    private final Color DARK_TEXT =
            new Color(40, 50, 60);

    private final Color WHITE =
            Color.WHITE;

    public InterviewFrame() {

        interviews = new ArrayList<>();

        setTitle("InternTrack - Interviews");
        setSize(850, 680);
        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );
        setLocationRelativeTo(null);

        getContentPane().setBackground(
                LIGHT_BLUE
        );

        createInterface();

        refreshInterviews();

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
                        "INTERVIEW TRACKER",
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
                        "Keep track of your upcoming interview rounds",
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

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(
                                12,
                                12
                        )
                );

        centerPanel.setBackground(
                LIGHT_BLUE
        );

        JPanel formPanel =
                new JPanel(
                        new GridLayout(
                                6,
                                2,
                                10,
                                12
                        )
                );

        formPanel.setBackground(
                WHITE
        );

        formPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                new Color(
                                        210,
                                        220,
                                        232
                                )
                        ),
                        BorderFactory.createTitledBorder(
                                BorderFactory.createEmptyBorder(
                                        10,
                                        10,
                                        10,
                                        10
                                ),
                                "Interview Details"
                        )
                )
        );

        companyField = new JTextField();
        roleField = new JTextField();
        dateField = new JTextField();
        roundField = new JTextField();

        modeBox =
                new JComboBox<>(
                        new String[]{
                                "Online",
                                "Offline"
                        }
                );

        resultBox =
                new JComboBox<>(
                        new String[]{
                                "Scheduled",
                                "Pending",
                                "Selected",
                                "Rejected"
                        }
                );

        addField(
                formPanel,
                "Company Name:",
                companyField
        );

        addField(
                formPanel,
                "Job Role:",
                roleField
        );

        addField(
                formPanel,
                "Interview Date:",
                dateField
        );

        addField(
                formPanel,
                "Round:",
                roundField
        );

        addField(
                formPanel,
                "Mode:",
                modeBox
        );

        addField(
                formPanel,
                "Result:",
                resultBox
        );

        centerPanel.add(
                formPanel,
                BorderLayout.NORTH
        );

        JPanel notesPanel =
                new JPanel(
                        new BorderLayout()
                );

        notesPanel.setBackground(
                WHITE
        );

        notesPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                new Color(
                                        210,
                                        220,
                                        232
                                )
                        ),
                        BorderFactory.createTitledBorder(
                                BorderFactory.createEmptyBorder(
                                        8,
                                        8,
                                        8,
                                        8
                                ),
                                "Interview Notes"
                        )
                )
        );

        notesArea =
                new JTextArea();

        notesArea.setLineWrap(true);
        notesArea.setWrapStyleWord(true);

        notesArea.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        notesArea.setBackground(
                WHITE
        );

        notesPanel.add(
                new JScrollPane(notesArea),
                BorderLayout.CENTER
        );

        centerPanel.add(
                notesPanel,
                BorderLayout.CENTER
        );

        JPanel buttonPanel =
                new JPanel();

        buttonPanel.setBackground(
                LIGHT_BLUE
        );

        JButton addButton =
                createButton(
                        "Add Interview",
                        GREEN
                );

        JButton clearButton =
                createButton(
                        "Clear",
                        new Color(
                                110,
                                120,
                                130
                        )
                );

        JButton refreshButton =
                createButton(
                        "Refresh",
                        BLUE
                );

        buttonPanel.add(addButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(refreshButton);

        centerPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        interviewArea =
                new JTextArea();

        interviewArea.setEditable(false);
        interviewArea.setLineWrap(true);
        interviewArea.setWrapStyleWord(true);

        interviewArea.setBackground(
                WHITE
        );

        interviewArea.setForeground(
                DARK_TEXT
        );

        interviewArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        13
                )
        );

        interviewArea.setBorder(
                new EmptyBorder(
                        12,
                        12,
                        12,
                        12
                )
        );

        JScrollPane interviewScroll =
                new JScrollPane(
                        interviewArea
                );

        interviewScroll.setBorder(
                BorderFactory.createTitledBorder(
                        new LineBorder(
                                new Color(
                                        210,
                                        220,
                                        232
                                )
                        ),
                        "Scheduled Interviews"
                )
        );

        interviewScroll.setPreferredSize(
                new Dimension(
                        800,
                        220
                )
        );

        mainPanel.add(
                interviewScroll,
                BorderLayout.SOUTH
        );

        addButton.addActionListener(
                e -> addInterview()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );

        refreshButton.addActionListener(
                e -> refreshInterviews()
        );

        add(mainPanel);
    }

    private void addField(
            JPanel panel,
            String labelText,
            JComponent component) {

        JLabel label =
                new JLabel(labelText);

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        label.setForeground(
                DARK_TEXT
        );

        panel.add(label);
        panel.add(component);
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

    private void addInterview() {

        String company =
                companyField
                        .getText()
                        .trim();

        String role =
                roleField
                        .getText()
                        .trim();

        String date =
                dateField
                        .getText()
                        .trim();

        String round =
                roundField
                        .getText()
                        .trim();

        String mode =
                modeBox
                        .getSelectedItem()
                        .toString();

        String result =
                resultBox
                        .getSelectedItem()
                        .toString();

        String notes =
                notesArea
                        .getText()
                        .trim();

        if (company.isEmpty() ||
            role.isEmpty() ||
            date.isEmpty() ||
            round.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all required details.",
                    "Incomplete Details",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        Interview interview =
                new Interview(
                        company,
                        role,
                        date,
                        round,
                        mode,
                        result,
                        notes
                );

        interviews.add(interview);

        JOptionPane.showMessageDialog(
                this,
                "Interview added successfully.",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );

        clearFields();

        refreshInterviews();
    }

    private void refreshInterviews() {

        interviewArea.setText("");

        if (interviews.isEmpty()) {

            interviewArea.setText(
                    "No interviews scheduled yet."
            );

            return;
        }

        for (
                Interview interview :
                interviews
        ) {

            interviewArea.append(
                    "COMPANY  : "
                    + interview.getCompanyName()
                    + "\n"
            );

            interviewArea.append(
                    "ROLE     : "
                    + interview.getRole()
                    + "\n"
            );

            interviewArea.append(
                    "DATE     : "
                    + interview.getInterviewDate()
                    + "\n"
            );

            interviewArea.append(
                    "ROUND    : "
                    + interview.getRound()
                    + "\n"
            );

            interviewArea.append(
                    "MODE     : "
                    + interview.getMode()
                    + "\n"
            );

            interviewArea.append(
                    "RESULT   : "
                    + interview.getResult()
                    + "\n"
            );

            interviewArea.append(
                    "NOTES    : "
                    + interview.getNotes()
                    + "\n"
            );

            interviewArea.append(
                    "DETAILS  : "
                    + interview
                    + "\n"
            );

            interviewArea.append(
                    "----------------------------------------\n"
            );
        }

        interviewArea.setCaretPosition(0);
    }

    private void clearFields() {

        companyField.setText("");
        roleField.setText("");
        dateField.setText("");
        roundField.setText("");
        notesArea.setText("");

        modeBox.setSelectedIndex(0);
        resultBox.setSelectedIndex(0);
    }
}