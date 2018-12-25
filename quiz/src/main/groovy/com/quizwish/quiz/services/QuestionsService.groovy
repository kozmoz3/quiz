package com.quizwish.quiz.services

import com.quizwish.quiz.entity.Grupo
import com.quizwish.quiz.entity.Grupousuario
import com.quizwish.quiz.entity.Questions
import com.quizwish.quiz.models.User
import com.quizwish.quiz.models.jmodelos.MQuestion

interface QuestionsService {
	def getQuestionsAll();
	
	def getQuestionsById(Integer id);
				
	def setQuestion(MQuestion question);
			
	def deleteQuestions(Integer id);
	
	def setQuestions(List<MQuestion> lstQuest)
}
