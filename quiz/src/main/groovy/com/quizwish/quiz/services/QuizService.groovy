package com.quizwish.quiz.services

<<<<<<< HEAD
import org.springframework.data.domain.Example

import com.quizwish.quiz.models.Quiz
=======
import com.quizwish.quiz.entity.Quiz
>>>>>>> autentication

interface QuizService {

	def save(Quiz quiz);
	
	def getQuizAll();
	
	def getQuizById(Integer id);
				
	def setQuiz(Quiz quiz);
			
	def deleteQuiz(Integer id);
			
	def getByCriteria(Example<Quiz> example);
	
	def getByCriteriaList(Example<Quiz> example);
}
