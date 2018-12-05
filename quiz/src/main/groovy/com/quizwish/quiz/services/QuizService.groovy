package com.quizwish.quiz.services


import org.hibernate.criterion.Example

import com.quizwish.quiz.entity.Quiz


interface QuizService {

	def save(Quiz quiz);
	
	def getQuizAll();
	
	def getQuizById(Integer id);
				
	def setQuiz(Quiz quiz);
			
	def deleteQuiz(Integer id);
			
	
}
