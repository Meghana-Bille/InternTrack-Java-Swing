package model;

public class AptitudeQuestion {

    private int questionId;
    private String questionText;
    private String[] options;
    private int correctOption;
    private String category;
    private int marks;

    public AptitudeQuestion(
            int questionId,
            String questionText,
            String[] options,
            int correctOption,
            String category,
            int marks) {

        this.questionId = questionId;
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
        this.category = category;
        this.marks = marks;
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }

    public String getCategory() {
        return category;
    }

    public int getMarks() {
        return marks;
    }

    public boolean isCorrect(int selectedOption) {
        return selectedOption == correctOption;
    }

    public boolean hasValidOptions() {
        return options != null && options.length > 0;
    }

    public boolean hasValidQuestion() {
        return questionText != null
                && !questionText.trim().isEmpty()
                && hasValidOptions()
                && correctOption >= 0
                && correctOption < options.length;
    }

    @Override
    public String toString() {

        return questionId
                + " - "
                + category
                + " - "
                + questionText;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof AptitudeQuestion)) {
            return false;
        }

        AptitudeQuestion question =
                (AptitudeQuestion) obj;

        return questionId == question.questionId;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(questionId);
    }
}