package com.quizwish.quiz.services

import com.quizwish.quiz.models.Quiz
import com.quizwish.quiz.repositorys.QuizRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.domain.Example
import org.springframework.stereotype.Service

@Service("serviceQuiz")
class QuizServiceImp {
	@Autowired
	@Qualifier("quizRepository")
	QuizRepository quizRepository
	
	def List<Quiz> getQuizAll() {
		return quizRepository.findAll()
	}
	
	def Quiz getQuizById(Integer id) {
		return quizRepository.findById(id)
	}
	
	def Quiz setQuiz(Quiz quiz) {
		return quizRepository.save(quiz)
	}
	
	def deleteQuiz(Integer id) {
		quizRepository.deleteById(id)
	}
	
	def Optional<Quiz> getByCriteria(Example<Quiz> example) {
		return quizRepository.findAll(example)
	}
}
