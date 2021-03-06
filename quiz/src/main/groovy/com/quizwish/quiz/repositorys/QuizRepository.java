package com.quizwish.quiz.repositorys;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quizwish.quiz.entity.Quiz;
import com.quizwish.quiz.models.User;

@Repository("quizRepository")
public interface QuizRepository extends JpaRepository<Quiz, Serializable>{

	public abstract List<Quiz> findAllByIduser(User iduser);
	
	
}
