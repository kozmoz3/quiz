package com.quizwish.quiz.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quizwish.quiz.models.Quiz;

@Repository("quizRepository")
public interface QuizRepository extends JpaRepository<Quiz, Integer>{

}
