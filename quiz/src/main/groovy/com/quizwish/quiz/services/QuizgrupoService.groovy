package com.quizwish.quiz.services

import com.quizwish.quiz.entity.Quiz
import com.quizwish.quiz.entity.Quizgrupo

interface QuizgrupoService {
	def saveQuizgrupo(Quizgrupo quizgrupo)
	public abstract List<Quizgrupo> findAllByIdquiz(Quiz idquiz)
}
