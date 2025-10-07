package com.example.quizApp.Service;

import com.example.quizApp.Model.Questions;
import com.example.quizApp.Repository.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionsService {

    @Autowired
    QuestionsRepository questionsRepository;

    public void saveQuestion(Questions q){
        questionsRepository.save(q);
    }

    public void saveAllQuestions(List<Questions> q){
        questionsRepository.saveAll(q);
    }

    public Questions getRandomQuestion(int userId){
        return questionsRepository.findRandomQuestions(userId);
    }

    public Questions getQuestionById(int id) {
        return questionsRepository.findById(id).orElse(null);
    }
}
