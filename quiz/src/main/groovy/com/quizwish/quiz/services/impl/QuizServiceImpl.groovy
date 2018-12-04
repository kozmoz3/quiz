package com.quizwish.quiz.services.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.domain.Example
import org.springframework.stereotype.Service

<<<<<<< HEAD
import com.quizwish.quiz.models.Quiz
import com.quizwish.quiz.repositorys.QuizRepository
=======
import com.quizwish.quiz.entity.Quiz
>>>>>>> autentication
import com.quizwish.quiz.services.QuizService

@Service("quizService")
class QuizServiceImpl implements QuizService{
	
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
	def getQuizById(Integer id) {
		return quizRepository.findById(id)
	}

	@Override
	def setQuiz(Quiz quiz) {
		return quizRepository.save(quiz)
	}

	@Override
	def deleteQuiz(Integer id) {
		return quizRepository.deleteById(id)
	}

	@Override
	def getByCriteria(Example<Quiz> example) {
		return quizRepository.findOne(example)
	}

	@Override
	public Object getByCriteriaList(Example<Quiz> example) {
		return quizRepository.findAll(example);
	}
}
