package com.example.quizApp.Repository;

import com.example.quizApp.Model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionsRepository extends JpaRepository<Questions, Integer> {
    @Query(value = """
            SELECT * From questions WHERE q_id NOT IN (
            SELECT question_id From user_answers WHERE user_id = :uid) 
            ORDER BY RAND() LIMIT 1""", nativeQuery = true)
    Questions findRandomQuestions(@Param("uid") int uid);
}
