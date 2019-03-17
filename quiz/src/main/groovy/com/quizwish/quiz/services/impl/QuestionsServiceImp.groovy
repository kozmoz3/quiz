package com.quizwish.quiz.services.impl

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

import com.quizwish.quiz.component.QuestionComponent
import com.quizwish.quiz.entity.Questions
import com.quizwish.quiz.models.jmodelos.MQuestion
import com.quizwish.quiz.repositorys.QuestionsRepository
import com.quizwish.quiz.services.QuestionsService

@Service("questionsService")
class QuestionsServiceImp implements QuestionsService{
	
	private static final Log LOGGER = LogFactory.getLog(QuestionsServiceImp.class)
	
	@Autowired
	@Qualifier("questionsRepository")
	QuestionsRepository questionsRepository
	@Autowired
	@Qualifier("questioncomponent")
	QuestionComponent questioncomponent

	@Override
	def getQuestionsAll() {
		return questionsRepository.findAll()
	}

	@Override
	def getQuestionsById(Integer id) {
		Optional<Questions> optQuest = questionsRepository.findById(id)
		return optQuest.isPresent() ? optQuest.get() : new Questions()
	}

	@Override
	def setQuestion(MQuestion question) {
		Questions quiz = questioncomponent.setQuestion(question)
		if(quiz != null)		
			return questionsRepository.save(quiz)
		return quiz
	}

	@Override
	def deleteQuestions(Integer id) {
		return questionsRepository.deleteById(id)
	}

	@Override
	def setQuestions(List<MQuestion> lstQuest) {
		List<Questions> lstqustions = questioncomponent.setQuestions(lstQuest)
		if(!lstqustions.isEmpty())
			lstqustions.each { it -> questionsRepository.save(it) }
		return lstqustions;
	}

	@Override
	def getAllQuestionsById(List<Integer> ids) {
		return questionsRepository.findAllById(ids)
	}
	
}
