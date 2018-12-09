package com.quizwish.quiz.services


import org.hibernate.criterion.Example

import com.quizwish.quiz.entity.Quiz
import com.quizwish.quiz.models.User


interface QuizService {
	
     
	def save(Quiz quiz);
	
	def getQuizAll();
	
	public List<Quiz> getQuizByIduser(User user);
	
	
				
	def setQuiz(Quiz quiz);
			
	def deleteQuiz(Integer id);
			
	
}
