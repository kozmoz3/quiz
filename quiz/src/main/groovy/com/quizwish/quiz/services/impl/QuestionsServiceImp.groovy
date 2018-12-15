package com.quizwish.quiz.services.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

import com.quizwish.quiz.entity.Questions
import com.quizwish.quiz.repositorys.QuestionsRepository
import com.quizwish.quiz.services.QuestionsService

@Service("questionsService")
class QuestionsServiceImp implements QuestionsService{
	
	@Autowired
	@Qualifier("questionsRepository")
	QuestionsRepository questionsRepository

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
	def setQuestions(Questions question) {
		return questionsRepository.save(question)
	}

	@Override
	def deleteQuestions(Integer id) {
		return questionsRepository.deleteById(id)
	}
	
}
