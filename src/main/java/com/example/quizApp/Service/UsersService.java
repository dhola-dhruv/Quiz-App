package com.example.quizApp.Service;

import com.example.quizApp.DTO.QuestionDTO;
import com.example.quizApp.DTO.ResultDTO;
import com.example.quizApp.DTO.SubmitAnswerDTO;
import com.example.quizApp.DTO.SubmitAnswerResponse;
import com.example.quizApp.Model.Questions;
import com.example.quizApp.Model.UserAnswer;
import com.example.quizApp.Model.Users;
import com.example.quizApp.Repository.UsersAnswerRepository;
import com.example.quizApp.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    QuestionsService questionsService;

    @Autowired
    UsersAnswerRepository usersAnswerRepository;

    public ResponseEntity<?> startQuiz(String userName){
        Users user = getOrCreateUserByName(userName);

        Questions question = questionsService.getRandomQuestion(user.getuId());

        if (question == null){
            return ResponseEntity.ok("No more questions available for this user.");
        }

        QuestionDTO questionDTO = new QuestionDTO(
                user.getuId(),
                question.getqId(),
                question.getQuestionText(),
                question.getOption_A(),
                question.getOption_B(),
                question.getOption_C(),
                question.getOption_D()
        );

        return ResponseEntity.ok(questionDTO);
    }

    public Users getOrCreateUserByName(String userName){
        Users user = usersRepository.findByUserName(userName);
        if (user == null){
            Users newUser = new Users();
            newUser.setUserName(userName);
            return usersRepository.save(newUser);
        }
        return user;
    }

    public ResponseEntity<?> submitAnswer(@RequestBody SubmitAnswerDTO answerDTO){
        Users user = getUserById(answerDTO.getUserId());

        if (user == null) {
            return ResponseEntity.badRequest().body("User not found.");
        }

        if (user.getQuestionAttemptedCount() == 5) {
            return ResponseEntity.ok("Quiz completed! You've answered the maximum number of questions.");
        }

        Questions question = questionsService.getQuestionById(answerDTO.getQuestionId());
        if (question == null) {
            return ResponseEntity.badRequest().body("Invalid question ID.");
        }

        if (usersAnswerRepository.findByUsersAndQuestions(user.getuId(), question.getqId()) != null){
            return ResponseEntity.badRequest().body("You have already answered this question");
        }

        boolean isCorrect = question.getCorrectOption().equalsIgnoreCase(answerDTO.getSelectedOption());

        UserAnswer userAnswer = new UserAnswer();
        userAnswer.setUsers(user);
        userAnswer.setQuestions(question);
        userAnswer.setSelectedAnswer(answerDTO.getSelectedOption());
        userAnswer.setCorrectAnswer(isCorrect);

        if (isCorrect){
            user.setCorrectAnswerCount(user.getCorrectAnswerCount() + 1);
        }
        else {
            user.setWrongAnswerCount(user.getWrongAnswerCount() + 1);
        }

        saveUserAnswer(userAnswer);

        user.setQuestionAttemptedCount(user.getQuestionAttemptedCount() + 1);
        saveUser(user);

        if (user.getQuestionAttemptedCount() == 5) {
            return ResponseEntity.ok(new ResultDTO(
                    user.getuId(),
                    user.getUserName(),
                    user.getCorrectAnswerCount(),
                    user.getWrongAnswerCount()));
        }

        Questions nextQuestion = questionsService.getRandomQuestion(user.getuId());
        if (nextQuestion == null) {
            return ResponseEntity.ok("No more questions available.");
        }

        QuestionDTO nextQuestionDTO = new QuestionDTO(
                user.getuId(),
                nextQuestion.getqId(),
                nextQuestion.getQuestionText(),
                nextQuestion.getOption_A(),
                nextQuestion.getOption_B(),
                nextQuestion.getOption_C(),
                nextQuestion.getOption_D()
        );

        return ResponseEntity.ok(new SubmitAnswerResponse(
                isCorrect,
                nextQuestionDTO,
                user.getCorrectAnswerCount(),
                user.getWrongAnswerCount(),
                false));
    }

    public Users getUserById(int userId) {
        return usersRepository.findById(userId).orElse(null);
    }

    public Users saveUser(Users user) {
        return usersRepository.save(user);
    }

    public void saveUserAnswer(UserAnswer answer) {
        usersAnswerRepository.save(answer);
    }

    public ResponseEntity<?> result(int userId) {
        Users user = getUserById(userId);
        return ResponseEntity.ok(new ResultDTO(
                user.getuId(),
                user.getUserName(),
                user.getCorrectAnswerCount(),
                user.getWrongAnswerCount()));
    }

}
