package com.example.quizApp.Controller;

import com.example.quizApp.Model.Questions;
import com.example.quizApp.Service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionsController {

    @Autowired
    QuestionsService questionsService;

    @PostMapping("save")
    public void saveQuestion(@RequestBody Questions q){
        questionsService.saveQuestion(q);
    }

    @PostMapping("saveAll")
    public void saveAllQuestions(@RequestBody List<Questions> q){
        questionsService.saveAllQuestions(q);
    }

}
