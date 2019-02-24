package com.quizwish.quiz.services


import org.hibernate.criterion.Example

import com.quizwish.quiz.entity.Quiz
import com.quizwish.quiz.models.User


interface QuizService {
	
	public abstract Quiz saveQuiz(Map<String,Object> quiz, User user);
	 
	def save(Quiz quiz);
	
	def getQuizAll();
	
	public List<Quiz> getQuizByIduser(User user);
	
	def getQuizById(Integer id);
				
	def setQuiz(Quiz quiz);
			
	def deleteQuiz(Integer id);
	
	def relacionQuizGrupo(Integer idquiz, Integer grupo, boolean satus)
	
}
