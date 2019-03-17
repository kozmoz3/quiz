package com.quizwish.quiz.services

import com.quizwish.quiz.entity.Quiz
import com.quizwish.quiz.entity.Respuestas

interface RespuestasService {
	def save(Respuestas respuestas)
	def deleteRespuestasById(Integer id)
	def getRespuestasById(Integer id)
	def getAllRespuestasById(List<Integer> ids)
	def getRespuestasAll(Quiz idquiz, Integer idStudent)
}
