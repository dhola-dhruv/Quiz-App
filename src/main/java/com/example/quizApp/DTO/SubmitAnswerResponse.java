package com.example.quizApp.DTO;

public class SubmitAnswerResponse {
    private boolean isCorrect;
    private QuestionDTO nextQuestion;
    private boolean quizCompleted;

    private int correctAnswerCount;
    private int wrongAnswerCount;


    public SubmitAnswerResponse(boolean isCorrect, QuestionDTO nextQuestion,
                                int correctAnswerCount, int wrongAnswerCount,boolean quizCompleted) {
        this.isCorrect = isCorrect;
        this.nextQuestion = nextQuestion;
        this.correctAnswerCount = correctAnswerCount;
        this.wrongAnswerCount = wrongAnswerCount;
        this.quizCompleted = quizCompleted;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public QuestionDTO getNextQuestion() {
        return nextQuestion;
    }

    public void setNextQuestion(QuestionDTO nextQuestion) {
        this.nextQuestion = nextQuestion;
    }

    public boolean isQuizCompleted() {
        return quizCompleted;
    }

    public void setQuizCompleted(boolean quizCompleted) {
        this.quizCompleted = quizCompleted;
    }

    public int getCorrectAnswerCount() {
        return correctAnswerCount;
    }

    public void setCorrectAnswerCount(int correctAnswerCount) {
        this.correctAnswerCount = correctAnswerCount;
    }

    public int getWrongAnswerCount() {
        return wrongAnswerCount;
    }

    public void setWrongAnswerCount(int wrongAnswerCount) {
        this.wrongAnswerCount = wrongAnswerCount;
    }
}
