package ui;

import model.Application;
import model.AptitudeResult;
import model.Student;
import service.ApplicationManager;
import service.AptitudeResultManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class AptitudeTestFrame extends JFrame {

    private Student student;
    private ApplicationManager applicationManager;
    private Application application;

    private ArrayList<Question> questions;

    private int currentQuestion;
    private int timeLeft;

    private JLabel questionLabel;
    private JLabel questionNumberLabel;
    private JLabel timerLabel;

    private JRadioButton optionA;
    private JRadioButton optionB;
    private JRadioButton optionC;
    private JRadioButton optionD;

    private ButtonGroup optionGroup;

    private JButton previousButton;
    private JButton nextButton;
    private JButton submitButton;

    private Timer timer;

    private boolean submitted;

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

    private final Color DARK_TEXT =
            new Color(40, 50, 60);

    private final Color WHITE =
            Color.WHITE;

    public AptitudeTestFrame(
            Student student,
            ApplicationManager applicationManager,
            Application application) {

        this.student = student;
        this.applicationManager = applicationManager;
        this.application = application;

        this.questions = new ArrayList<>();
        this.currentQuestion = 0;
        this.timeLeft = 90;
        this.submitted = false;

        loadQuestions();

        if (application == null) {

            JOptionPane.showMessageDialog(
                    null,
                    "No application was selected.",
                    "Aptitude Test",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (student == null
                || student.getEmail() == null
                || student.getEmail().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    null,
                    "Student email could not be identified.",
                    "Aptitude Test",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (hasCompletedTest()) {
            return;
        }

        setTitle(
                "InternTrack - Aptitude Test"
        );

        setSize(
                850,
                600
        );

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);

        getContentPane().setBackground(
                LIGHT_BLUE
        );

        createInterface();

        showQuestion();

        startTimer();

        setVisible(true);
    }

    private boolean hasCompletedTest() {

        try {

            boolean completed =
                    AptitudeResultManager.hasResultForApplication(
                            student.getEmail(),
                            application.getApplicationId()
                    );

            if (completed) {

                JOptionPane.showMessageDialog(
                        null,
                        "The aptitude test for Application ID "
                                + application.getApplicationReference()
                                + " has already been completed.",
                        "Test Already Completed",
                        JOptionPane.INFORMATION_MESSAGE
                );

                return true;
            }

        } catch (IOException ex) {

            JOptionPane.showMessageDialog(
                    null,
                    "Unable to verify previous test results.\n\n"
                            + ex.getMessage(),
                    "Result Check Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return true;
        }

        return false;
    }

    private void loadQuestions() {

        questions.add(
                new Question(
                        "Which data structure follows the FIFO principle?",
                        "Stack",
                        "Queue",
                        "Tree",
                        "Graph",
                        "Queue"
                )
        );

        questions.add(
                new Question(
                        "What is the time complexity of binary search?",
                        "O(n)",
                        "O(log n)",
                        "O(n²)",
                        "O(1)",
                        "O(log n)"
                )
        );

        questions.add(
                new Question(
                        "Which keyword is used to inherit a class in Java?",
                        "implements",
                        "inherits",
                        "extends",
                        "super",
                        "extends"
                )
        );

        questions.add(
                new Question(
                        "Which SQL command is used to retrieve data?",
                        "INSERT",
                        "SELECT",
                        "UPDATE",
                        "DELETE",
                        "SELECT"
                )
        );

        questions.add(
                new Question(
                        "Which of the following is not an OOP principle?",
                        "Encapsulation",
                        "Inheritance",
                        "Compilation",
                        "Polymorphism",
                        "Compilation"
                )
        );

        questions.add(
                new Question(
                        "Which protocol is mainly used to transfer web pages?",
                        "HTTP",
                        "FTP",
                        "SMTP",
                        "SNMP",
                        "HTTP"
                )
        );

        questions.add(
                new Question(
                        "What is the output of 10 / 2 + 3 in Java?",
                        "8",
                        "6",
                        "5",
                        "7",
                        "8"
                )
        );

        questions.add(
                new Question(
                        "Which memory area stores objects in Java?",
                        "Stack",
                        "Heap",
                        "Register",
                        "Cache",
                        "Heap"
                )
        );

        questions.add(
                new Question(
                        "Which algorithm is commonly used for traversing a graph level by level?",
                        "DFS",
                        "BFS",
                        "Binary Search",
                        "Quick Sort",
                        "BFS"
                )
        );

        questions.add(
                new Question(
                        "Which keyword prevents a Java method from being overridden?",
                        "static",
                        "final",
                        "private",
                        "protected",
                        "final"
                )
        );
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
                        new BorderLayout()
                );

        headerPanel.setBackground(
                LIGHT_BLUE
        );

        JPanel titlePanel =
                new JPanel(
                        new GridLayout(
                                3,
                                1,
                                3,
                                3
                        )
                );

        titlePanel.setBackground(
                LIGHT_BLUE
        );

        JLabel title =
                new JLabel(
                        "APTITUDE ASSESSMENT",
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

        JLabel companyLabel =
                new JLabel(
                        application.getCompanyName()
                                + " - "
                                + application.getRole(),
                        SwingConstants.CENTER
                );

        companyLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        companyLabel.setForeground(
                DARK_TEXT
        );

        JLabel referenceLabel =
                new JLabel(
                        "Application ID: "
                                + application.getApplicationReference(),
                        SwingConstants.CENTER
                );

        referenceLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        referenceLabel.setForeground(
                BLUE
        );

        titlePanel.add(title);
        titlePanel.add(companyLabel);
        titlePanel.add(referenceLabel);

        headerPanel.add(
                titlePanel,
                BorderLayout.CENTER
        );

        timerLabel =
                new JLabel(
                        "Time: 01:30",
                        SwingConstants.CENTER
                );

        timerLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        timerLabel.setForeground(
                BLUE
        );

        timerLabel.setPreferredSize(
                new Dimension(
                        130,
                        40
                )
        );

        headerPanel.add(
                timerLabel,
                BorderLayout.EAST
        );

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        JPanel questionPanel =
                new JPanel(
                        new BorderLayout(
                                15,
                                15
                        )
                );

        questionPanel.setBackground(
                WHITE
        );

        questionPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                new Color(
                                        210,
                                        220,
                                        232
                                )
                        ),
                        new EmptyBorder(
                                20,
                                20,
                                20,
                                20
                        )
                )
        );

        JPanel questionHeader =
                new JPanel(
                        new BorderLayout()
                );

        questionHeader.setBackground(
                WHITE
        );

        questionNumberLabel =
                new JLabel();

        questionNumberLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        questionNumberLabel.setForeground(
                BLUE
        );

        questionHeader.add(
                questionNumberLabel,
                BorderLayout.WEST
        );

        questionLabel =
                new JLabel();

        questionLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        questionLabel.setForeground(
                DARK_TEXT
        );

        questionHeader.add(
                questionLabel,
                BorderLayout.CENTER
        );

        questionPanel.add(
                questionHeader,
                BorderLayout.NORTH
        );

        JPanel optionsPanel =
                new JPanel(
                        new GridLayout(
                                4,
                                1,
                                10,
                                10
                        )
                );

        optionsPanel.setBackground(
                WHITE
        );

        optionA = new JRadioButton();
        optionB = new JRadioButton();
        optionC = new JRadioButton();
        optionD = new JRadioButton();

        styleOption(optionA);
        styleOption(optionB);
        styleOption(optionC);
        styleOption(optionD);

        optionGroup =
                new ButtonGroup();

        optionGroup.add(optionA);
        optionGroup.add(optionB);
        optionGroup.add(optionC);
        optionGroup.add(optionD);

        optionsPanel.add(optionA);
        optionsPanel.add(optionB);
        optionsPanel.add(optionC);
        optionsPanel.add(optionD);

        questionPanel.add(
                optionsPanel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                questionPanel,
                BorderLayout.CENTER
        );

        JPanel buttonPanel =
                new JPanel();

        buttonPanel.setBackground(
                LIGHT_BLUE
        );

        previousButton =
                createButton(
                        "Previous",
                        new Color(
                                110,
                                120,
                                130
                        )
                );

        nextButton =
                createButton(
                        "Next",
                        BLUE
                );

        submitButton =
                createButton(
                        "Submit Test",
                        GREEN
                );

        buttonPanel.add(previousButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(submitButton);

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        previousButton.addActionListener(
                e -> previousQuestion()
        );

        nextButton.addActionListener(
                e -> nextQuestion()
        );

        submitButton.addActionListener(
                e -> submitTest()
        );

        add(mainPanel);
    }

    private void styleOption(
            JRadioButton option) {

        option.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        option.setBackground(
                WHITE
        );

        option.setForeground(
                DARK_TEXT
        );

        option.setFocusPainted(
                false
        );
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

        button.setFocusPainted(
                false
        );

        button.setOpaque(
                true
        );

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

    private void showQuestion() {

        Question question =
                questions.get(currentQuestion);

        questionNumberLabel.setText(
                "Question "
                        + (currentQuestion + 1)
                        + " of "
                        + questions.size()
                        + "     "
        );

        questionLabel.setText(
                question.getQuestion()
        );

        optionA.setText(
                question.getOptionA()
        );

        optionB.setText(
                question.getOptionB()
        );

        optionC.setText(
                question.getOptionC()
        );

        optionD.setText(
                question.getOptionD()
        );

        optionGroup.clearSelection();

        String selectedAnswer =
                question.getSelectedAnswer();

        if (selectedAnswer != null) {

            if (selectedAnswer.equals(optionA.getText())) {
                optionA.setSelected(true);
            } else if (selectedAnswer.equals(optionB.getText())) {
                optionB.setSelected(true);
            } else if (selectedAnswer.equals(optionC.getText())) {
                optionC.setSelected(true);
            } else if (selectedAnswer.equals(optionD.getText())) {
                optionD.setSelected(true);
            }
        }

        previousButton.setEnabled(
                currentQuestion > 0
        );

        nextButton.setEnabled(
                currentQuestion < questions.size() - 1
        );
    }

    private void saveCurrentAnswer() {

        Question question =
                questions.get(currentQuestion);

        question.setSelectedAnswer(
                getSelectedAnswer()
        );
    }

    private void nextQuestion() {

        saveCurrentAnswer();

        if (currentQuestion
                < questions.size() - 1) {

            currentQuestion++;

            showQuestion();
        }
    }

    private void previousQuestion() {

        saveCurrentAnswer();

        if (currentQuestion > 0) {

            currentQuestion--;

            showQuestion();
        }
    }

    private void startTimer() {

        timer =
                new Timer(
                        1000,
                        e -> {

                            timeLeft--;

                            updateTimerLabel();

                            if (timeLeft <= 0) {

                                timer.stop();

                                saveCurrentAnswer();

                                JOptionPane.showMessageDialog(
                                        this,
                                        "Time is up. Your test will be submitted automatically.",
                                        "Time Up",
                                        JOptionPane.WARNING_MESSAGE
                                );

                                submitWithoutConfirmation();
                            }
                        }
                );

        timer.start();
    }

    private void updateTimerLabel() {

        int minutes =
                timeLeft / 60;

        int seconds =
                timeLeft % 60;

        timerLabel.setText(
                String.format(
                        "Time: %02d:%02d",
                        minutes,
                        seconds
                )
        );

        if (timeLeft <= 20) {

            timerLabel.setForeground(
                    RED
            );

        } else {

            timerLabel.setForeground(
                    BLUE
            );
        }
    }

    private void submitTest() {

        if (submitted) {
            return;
        }

        saveCurrentAnswer();

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Submit the aptitude test now?",
                        "Confirm Submission",
                        JOptionPane.YES_NO_OPTION
                );

        if (choice == JOptionPane.YES_OPTION) {

            submitWithoutConfirmation();
        }
    }

    private void submitWithoutConfirmation() {

        if (submitted) {
            return;
        }

        submitted = true;

        if (timer != null) {
            timer.stop();
        }

        saveCurrentAnswer();

        int correctAnswers =
                calculateScore();

        int totalQuestions =
                questions.size();

        int percentage =
                (correctAnswers * 100)
                        / totalQuestions;

        boolean passed =
                percentage >= 60;

        String status =
                passed
                        ? "Shortlisted"
                        : "Rejected";

        applicationManager.updateApplicationStatus(
                application,
                status
        );

        AptitudeResult result =
                new AptitudeResult(
                        application.getApplicationId(),
                        application.getCompanyName(),
                        application.getRole(),
                        correctAnswers,
                        correctAnswers,
                        totalQuestions,
                        percentage,
                        passed,
                        LocalDate.now().toString()
                );

        try {

            AptitudeResultManager.saveResult(
                    result,
                    student.getEmail()
            );

        } catch (IOException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "The test was evaluated, but the result could not be saved.\n\n"
                            + ex.getMessage(),
                    "Save Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        String resultMessage =
                "APTITUDE TEST RESULT\n\n"
                        + "Application ID : "
                        + application.getApplicationReference()
                        + "\n"
                        + "Company        : "
                        + application.getCompanyName()
                        + "\n"
                        + "Role           : "
                        + application.getRole()
                        + "\n\n"
                        + "Score          : "
                        + correctAnswers
                        + "/"
                        + totalQuestions
                        + "\n"
                        + "Percentage     : "
                        + percentage
                        + "%\n"
                        + "Result         : "
                        + (passed
                        ? "PASSED"
                        : "NOT CLEARED")
                        + "\n"
                        + "Application    : "
                        + status;

        JOptionPane.showMessageDialog(
                this,
                resultMessage,
                "Aptitude Test Result",
                passed
                        ? JOptionPane.INFORMATION_MESSAGE
                        : JOptionPane.WARNING_MESSAGE
        );

        dispose();
    }

    private int calculateScore() {

        int score = 0;

        for (Question question : questions) {

            if (question.getSelectedAnswer() != null
                    && question.getSelectedAnswer().equals(
                            question.getCorrectAnswer()
                    )) {

                score++;
            }
        }

        return score;
    }

    private String getSelectedAnswer() {

        if (optionA.isSelected()) {
            return optionA.getText();
        }

        if (optionB.isSelected()) {
            return optionB.getText();
        }

        if (optionC.isSelected()) {
            return optionC.getText();
        }

        if (optionD.isSelected()) {
            return optionD.getText();
        }

        return null;
    }

    @Override
    public void dispose() {

        if (timer != null) {
            timer.stop();
        }

        super.dispose();
    }

    private static class Question {

        private String question;
        private String optionA;
        private String optionB;
        private String optionC;
        private String optionD;
        private String correctAnswer;
        private String selectedAnswer;

        public Question(
                String question,
                String optionA,
                String optionB,
                String optionC,
                String optionD,
                String correctAnswer) {

            this.question = question;
            this.optionA = optionA;
            this.optionB = optionB;
            this.optionC = optionC;
            this.optionD = optionD;
            this.correctAnswer = correctAnswer;
            this.selectedAnswer = null;
        }

        public String getQuestion() {
            return question;
        }

        public String getOptionA() {
            return optionA;
        }

        public String getOptionB() {
            return optionB;
        }

        public String getOptionC() {
            return optionC;
        }

        public String getOptionD() {
            return optionD;
        }

        public String getCorrectAnswer() {
            return correctAnswer;
        }

        public String getSelectedAnswer() {
            return selectedAnswer;
        }

        public void setSelectedAnswer(
                String selectedAnswer) {

            this.selectedAnswer = selectedAnswer;
        }
    }
}