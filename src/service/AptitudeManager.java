package service;

import model.AptitudeQuestion;

import java.util.ArrayList;

public class AptitudeManager {

    private ArrayList<AptitudeQuestion> questions;

    private boolean testCompleted;

    private int score;

    private int correctAnswers;

    public AptitudeManager() {

        questions = new ArrayList<>();

        testCompleted = false;

        score = 0;

        correctAnswers = 0;

        loadQuestions();
    }

    private void loadQuestions() {

        questions.add(
                new AptitudeQuestion(
                        1,
                        "If 20% of a number is 40, what is the number?",
                        new String[]{
                                "100",
                                "150",
                                "200",
                                "250"
                        },
                        3,
                        "Quantitative Aptitude",
                        1
                )
        );

        questions.add(
                new AptitudeQuestion(
                        2,
                        "Find the next number: 2, 6, 12, 20, ?",
                        new String[]{
                                "24",
                                "30",
                                "32",
                                "36"
                        },
                        2,
                        "Logical Reasoning",
                        1
                )
        );

        questions.add(
                new AptitudeQuestion(
                        3,
                        "A shop gives a 10% discount on an item priced at ₹500. What is the selling price?",
                        new String[]{
                                "₹450",
                                "₹460",
                                "₹480",
                                "₹490"
                        },
                        1,
                        "Quantitative Aptitude",
                        1
                )
        );

        questions.add(
                new AptitudeQuestion(
                        4,
                        "Choose the word closest in meaning to 'Rapid'.",
                        new String[]{
                                "Slow",
                                "Quick",
                                "Weak",
                                "Late"
                        },
                        2,
                        "Verbal Ability",
                        1
                )
        );

        questions.add(
                new AptitudeQuestion(
                        5,
                        "If CAT is coded as DBU, how is DOG coded using the same pattern?",
                        new String[]{
                                "EPH",
                                "EQH",
                                "FPH",
                                "EOH"
                        },
                        1,
                        "Logical Reasoning",
                        1
                )
        );

        questions.add(
                new AptitudeQuestion(
                        6,
                        "A train travels 120 km in 2 hours. What is its average speed?",
                        new String[]{
                                "40 km/h",
                                "50 km/h",
                                "60 km/h",
                                "80 km/h"
                        },
                        3,
                        "Quantitative Aptitude",
                        1
                )
        );

        questions.add(
                new AptitudeQuestion(
                        7,
                        "Choose the correctly spelled word.",
                        new String[]{
                                "Accomodation",
                                "Accommodation",
                                "Acommodtion",
                                "Accommadation"
                        },
                        2,
                        "Verbal Ability",
                        1
                )
        );

        questions.add(
                new AptitudeQuestion(
                        8,
                        "If all roses are flowers and some flowers are red, which statement is definitely true?",
                        new String[]{
                                "All roses are red",
                                "Some roses are red",
                                "All roses are flowers",
                                "No flowers are roses"
                        },
                        3,
                        "Logical Reasoning",
                        1
                )
        );

        questions.add(
                new AptitudeQuestion(
                        9,
                        "What is the simple interest on ₹1000 at 10% per annum for 2 years?",
                        new String[]{
                                "₹100",
                                "₹150",
                                "₹200",
                                "₹250"
                        },
                        3,
                        "Quantitative Aptitude",
                        1
                )
        );

        questions.add(
                new AptitudeQuestion(
                        10,
                        "Choose the opposite of 'Expand'.",
                        new String[]{
                                "Increase",
                                "Contract",
                                "Multiply",
                                "Extend"
                        },
                        2,
                        "Verbal Ability",
                        1
                )
        );
    }

    public ArrayList<AptitudeQuestion> getQuestions() {

        return questions;
    }

    public int getTotalQuestions() {

        return questions.size();
    }

    public int calculateScore(
            ArrayList<Integer> answers) {

        score = 0;

        correctAnswers = 0;

        for (
                int i = 0;
                i < questions.size();
                i++
        ) {

            if (i >= answers.size()) {
                continue;
            }

            int selectedOption =
                    answers.get(i);

            AptitudeQuestion question =
                    questions.get(i);

            if (
                    question.isCorrect(
                            selectedOption
                    )
            ) {

                score =
                        score
                        + question.getMarks();

                correctAnswers++;
            }
        }

        testCompleted = true;

        return score;
    }

    public int getScore() {

        return score;
    }

    public int getCorrectAnswers() {

        return correctAnswers;
    }

    public int getPercentage() {

        if (questions.isEmpty()) {

            return 0;
        }

        return
                (score * 100)
                / getTotalMarks();
    }

    public int getTotalMarks() {

        int totalMarks = 0;

        for (
                AptitudeQuestion question :
                questions
        ) {

            totalMarks =
                    totalMarks
                    + question.getMarks();
        }

        return totalMarks;
    }

    public boolean hasPassed() {

        return getPercentage() >= 60;
    }

    public boolean isTestCompleted() {

        return testCompleted;
    }

    public void resetTest() {

        testCompleted = false;

        score = 0;

        correctAnswers = 0;
    }
}
