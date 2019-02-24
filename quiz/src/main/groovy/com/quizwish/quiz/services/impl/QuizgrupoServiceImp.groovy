package com.quizwish.quiz.services.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import com.quizwish.quiz.entity.Quiz
import com.quizwish.quiz.entity.Quizgrupo
import com.quizwish.quiz.repositorys.QuizgrupoRepository
import com.quizwish.quiz.services.QuizgrupoService

@Service("quizgrupoService")
class QuizgrupoServiceImp implements QuizgrupoService{
	@Autowired
	@Qualifier("quizgrupoRepository")
	QuizgrupoRepository quizgrupoRepository

	@Override
	public List<Quizgrupo> findAllByIdquiz(Quiz idquiz) {
		return quizgrupoRepository.findAllByIdquiz(idquiz)
	}

	@Override
	public Quizgrupo saveQuizgrupo(Quizgrupo quizgrupo) {
		return quizgrupoRepository.save(quizgrupo)
	}
}
