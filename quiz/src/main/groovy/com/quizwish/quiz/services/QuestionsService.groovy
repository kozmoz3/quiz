package com.quizwish.quiz.services

import com.quizwish.quiz.entity.Grupo
import com.quizwish.quiz.entity.Grupousuario
import com.quizwish.quiz.entity.Questions
import com.quizwish.quiz.models.User

interface QuestionsService {
	def getQuestionsAll();
	
	def getQuestionsById(Integer id);
				
	def setQuestions(Questions question);
			
	def deleteQuestions(Integer id);
}
