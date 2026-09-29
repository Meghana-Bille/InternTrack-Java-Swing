package ui;

import model.Student;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField nameField;
    private JTextField registerField;
    private JTextField emailField;
    private JTextField phoneField;

    private JButton loginButton;
    private JButton clearButton;

    private final Color NAVY =
            new Color(30, 58, 95);

    private final Color BLUE =
            new Color(52, 101, 164);

    private final Color LIGHT_BLUE =
            new Color(235, 243, 252);

    private final Color WHITE =
            Color.WHITE;

    private final Color DARK_TEXT =
            new Color(40, 50, 60);

    public LoginFrame() {

        setTitle("InternTrack - Student Login");
        setSize(650, 550);
        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );
        setLocationRelativeTo(null);

        getContentPane().setBackground(
                LIGHT_BLUE
        );

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
                BorderFactory.createEmptyBorder(
                        25,
                        45,
                        25,
                        45
                )
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

        JLabel subtitle =
                new JLabel(
                        "Internship & Job Application Tracker",
                        SwingConstants.CENTER
                );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        subtitle.setForeground(
                DARK_TEXT
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

        headingPanel.add(title);
        headingPanel.add(subtitle);

        mainPanel.add(
                headingPanel,
                BorderLayout.NORTH
        );

        JPanel formPanel =
                new JPanel(
                        new GridLayout(
                                4,
                                2,
                                12,
                                18
                        )
                );

        formPanel.setBackground(
                WHITE
        );

        formPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(210, 220, 232)
                        ),
                        BorderFactory.createTitledBorder(
                                BorderFactory.createEmptyBorder(
                                        10,
                                        10,
                                        10,
                                        10
                                ),
                                "Student Details"
                        )
                )
        );

        JLabel nameLabel =
                new JLabel("Student Name:");

        JLabel registerLabel =
                new JLabel("Register Number:");

        JLabel emailLabel =
                new JLabel("Email:");

        JLabel phoneLabel =
                new JLabel("Phone:");

        nameLabel.setForeground(DARK_TEXT);
        registerLabel.setForeground(DARK_TEXT);
        emailLabel.setForeground(DARK_TEXT);
        phoneLabel.setForeground(DARK_TEXT);

        formPanel.add(nameLabel);

        nameField =
                new JTextField();

        formPanel.add(nameField);

        formPanel.add(registerLabel);

        registerField =
                new JTextField();

        formPanel.add(registerField);

        formPanel.add(emailLabel);

        emailField =
                new JTextField();

        formPanel.add(emailField);

        formPanel.add(phoneLabel);

        phoneField =
                new JTextField();

        formPanel.add(phoneField);

        mainPanel.add(
                formPanel,
                BorderLayout.CENTER
        );

        JPanel buttonPanel =
                new JPanel();

        buttonPanel.setBackground(
                LIGHT_BLUE
        );

        clearButton =
                new JButton("Clear");

        loginButton =
                new JButton("Continue");

        styleButton(
                clearButton,
                new Color(110, 120, 130)
        );

        styleButton(
                loginButton,
                BLUE
        );

        buttonPanel.add(clearButton);
        buttonPanel.add(loginButton);

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        clearButton.addActionListener(
                e -> clearFields()
        );

        loginButton.addActionListener(
                e -> login()
        );

        add(mainPanel);

        setVisible(true);
    }

    private void styleButton(
            JButton button,
            Color color) {

        button.setBackground(color);
        button.setForeground(Color.WHITE);

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        button.setFocusPainted(false);

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        20,
                        10,
                        20
                )
        );

        button.setOpaque(true);
    }

    private void login() {

        String name =
                nameField
                        .getText()
                        .trim();

        String registerNumber =
                registerField
                        .getText()
                        .trim();

        String email =
                emailField
                        .getText()
                        .trim();

        String phone =
                phoneField
                        .getText()
                        .trim();

        if (name.isEmpty() ||
            registerNumber.isEmpty() ||
            email.isEmpty() ||
            phone.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all student details.",
                    "Incomplete Details",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (!email.contains("@")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid email address.",
                    "Invalid Email",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        Student student =
                new Student(
                        name,
                        registerNumber,
                        email,
                        phone
                );

        dispose();

        new DashboardFrame(student);
    }

    private void clearFields() {

        nameField.setText("");
        registerField.setText("");
        emailField.setText("");
        phoneField.setText("");
    }
}