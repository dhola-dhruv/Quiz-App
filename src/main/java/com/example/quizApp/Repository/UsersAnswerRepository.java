package com.example.quizApp.Repository;

import com.example.quizApp.Model.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersAnswerRepository extends JpaRepository<UserAnswer, Integer> {
//    @Query(value = """
//            SELECT * FROM user_answers
//            WHERE user_id = ?1 AND question_id = ?2""", nativeQuery = true)
    @Query(value = """
            SELECT * FROM user_answers
            WHERE user_id = :userId AND question_id = :questionId""", nativeQuery = true)
    UserAnswer findByUsersAndQuestions(@Param("userId") int userId, @Param("questionId") int questionId);
}
