package com.example.quizApp.Controller;

import com.example.quizApp.DTO.SubmitAnswerDTO;
import com.example.quizApp.Service.QuestionsService;
import com.example.quizApp.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UsersController {

    @Autowired
    UsersService usersService;

    @Autowired
    QuestionsService questionsService;

    @GetMapping("startQuiz")
    public ResponseEntity<?> startQuiz(@RequestParam String userName){
        return usersService.startQuiz(userName);
    }

    @PostMapping("submitAnswer")
    public ResponseEntity<?> submitAnswer(@RequestBody SubmitAnswerDTO answerDTO) {
        return usersService.submitAnswer(answerDTO);
    }

    @GetMapping("result")
        public ResponseEntity<?> result(@RequestParam int userId){
        return usersService.result(userId);
    }

}
