package com.quizwish.quiz.services.impl

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.domain.Example
import org.springframework.stereotype.Service

import com.quizwish.quiz.entity.Quiz
import com.quizwish.quiz.models.User
import com.quizwish.quiz.repositorys.QuizRepository
import com.quizwish.quiz.services.QuizService

@Service("quizService")
class QuizServiceImpl implements QuizService{
	private static final Log LOGGER = LogFactory.getLog(QuizServiceImpl.class)
	
	@Autowired
	@Qualifier("quizRepository")
	def QuizRepository quizRepository
	
	
	
	@Override
	def save(Quiz quiz) {
		return quizRepository.save(quiz)
	}

	@Override
	def getQuizAll() {
		return quizRepository.findAll()
	}

	@Override
	public List<Quiz>  getQuizByIduser(User idUser) {
		LOGGER.info("METHOD : getQuizByIduse -- id user = "+ idUser);
		return quizRepository.findAllByIduser(idUser);
	}
	
	

	@Override
	def setQuiz(Quiz quiz) {
		return quizRepository.save(quiz)
	}

	@Override
	def deleteQuiz(Integer id) {
		return quizRepository.deleteById(id)
	}


}
