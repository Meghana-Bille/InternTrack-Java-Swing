package ui;

import model.Student;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ProfileFrame extends JFrame {

    private Student student;

    private JTextField nameField;
    private JTextField registerField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField skillField;

    private JTextArea skillsArea;

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

    public ProfileFrame(Student student) {

        this.student = student;

        setTitle("InternTrack - Student Profile");
        setSize(700, 650);
        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
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
                        30,
                        20,
                        30
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
                        "STUDENT PROFILE",
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
                        "Manage your personal details and skills",
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

        JPanel personalPanel =
                createPersonalPanel();

        JPanel skillsPanel =
                createSkillsPanel();

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

        centerPanel.add(
                personalPanel,
                BorderLayout.NORTH
        );

        centerPanel.add(
                skillsPanel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        JPanel bottomPanel =
                new JPanel();

        bottomPanel.setBackground(
                LIGHT_BLUE
        );

        JButton saveButton =
                createButton(
                        "Save Profile",
                        BLUE
                );

        JButton clearButton =
                createButton(
                        "Clear Fields",
                        new Color(
                                110,
                                120,
                                130
                        )
                );

        bottomPanel.add(saveButton);
        bottomPanel.add(clearButton);

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        saveButton.addActionListener(
                e -> saveProfile()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );

        add(mainPanel);
    }

    private JPanel createPersonalPanel() {

        JPanel formPanel =
                new JPanel(
                        new GridLayout(
                                4,
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
                                "Personal Details"
                        )
                )
        );

        addFormField(
                formPanel,
                "Student Name:",
                nameField =
                        new JTextField(
                                student.getName()
                        )
        );

        addFormField(
                formPanel,
                "Register Number:",
                registerField =
                        new JTextField(
                                student.getRegisterNumber()
                        )
        );

        registerField.setEditable(false);

        addFormField(
                formPanel,
                "Email:",
                emailField =
                        new JTextField(
                                student.getEmail()
                        )
        );

        addFormField(
                formPanel,
                "Phone:",
                phoneField =
                        new JTextField(
                                student.getPhone()
                        )
        );

        JPanel wrapper =
                new JPanel(
                        new BorderLayout()
                );

        wrapper.setBackground(
                LIGHT_BLUE
        );

        wrapper.add(
                formPanel,
                BorderLayout.CENTER
        );

        return wrapper;
    }

    private JPanel createSkillsPanel() {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
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
                        BorderFactory.createTitledBorder(
                                BorderFactory.createEmptyBorder(
                                        10,
                                        10,
                                        10,
                                        10
                                ),
                                "Skills"
                        )
                )
        );

        skillsArea =
                new JTextArea();

        skillsArea.setEditable(false);

        skillsArea.setBackground(
                WHITE
        );

        skillsArea.setForeground(
                DARK_TEXT
        );

        skillsArea.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        refreshSkills();

        JScrollPane skillsScroll =
                new JScrollPane(
                        skillsArea
                );

        skillsScroll.setBorder(
                new LineBorder(
                        new Color(
                                225,
                                230,
                                236
                        )
                )
        );

        panel.add(
                skillsScroll,
                BorderLayout.CENTER
        );

        JPanel skillInputPanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        skillInputPanel.setBackground(
                WHITE
        );

        skillField =
                new JTextField();

        skillField.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        JButton addSkillButton =
                createButton(
                        "Add Skill",
                        GREEN
                );

        skillInputPanel.add(
                skillField,
                BorderLayout.CENTER
        );

        skillInputPanel.add(
                addSkillButton,
                BorderLayout.EAST
        );

        panel.add(
                skillInputPanel,
                BorderLayout.SOUTH
        );

        addSkillButton.addActionListener(
                e -> addSkill()
        );

        return panel;
    }

    private void addFormField(
            JPanel panel,
            String labelText,
            JComponent field) {

        JLabel label =
                new JLabel(
                        labelText
                );

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
        panel.add(field);
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

    private void addSkill() {

        String skill =
                skillField
                        .getText()
                        .trim();

        if (skill.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a skill.",
                    "Empty Skill",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (student.hasSkill(skill)) {

            JOptionPane.showMessageDialog(
                    this,
                    "This skill is already in your profile.",
                    "Duplicate Skill",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        student.addSkill(skill);

        skillField.setText("");

        refreshSkills();

        JOptionPane.showMessageDialog(
                this,
                "Skill added successfully.",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void refreshSkills() {

        skillsArea.setText("");

        if (student.getSkills().isEmpty()) {

            skillsArea.setText(
                    "No skills added yet."
            );

            return;
        }

        for (
                String skill :
                student.getSkills()
        ) {

            skillsArea.append(
                    "• " + skill + "\n"
            );
        }
    }

    private void saveProfile() {

        String name =
                nameField
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
            email.isEmpty() ||
            phone.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all personal details.",
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

        student.setName(name);
        student.setEmail(email);
        student.setPhone(phone);

        JOptionPane.showMessageDialog(
                this,
                "Profile updated successfully.",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void clearFields() {

        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        skillField.setText("");
    }
}