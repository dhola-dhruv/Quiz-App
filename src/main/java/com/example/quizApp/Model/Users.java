package com.example.quizApp.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uId;
    private String userName;
    private int questionAttemptedCount = 0;
    private int correctAnswerCount  = 0;
    private int wrongAnswerCount = 0;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<UserAnswer> userAnswer;

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public List<UserAnswer> getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(List<UserAnswer> userAnswer) {
        this.userAnswer = userAnswer;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getQuestionAttemptedCount() {
        return questionAttemptedCount;
    }

    public void setQuestionAttemptedCount(int questionAttemptedCount) {
        this.questionAttemptedCount = questionAttemptedCount;
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
