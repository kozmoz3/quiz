package com.quizwish.quiz.services.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

import com.quizwish.quiz.entity.Quiz
import com.quizwish.quiz.entity.Respuestas
import com.quizwish.quiz.repositorys.RespuestasRepository
import com.quizwish.quiz.services.RespuestasService

@Service("respuestasService")
class RespuestasServiceImp implements RespuestasService{
	
	@Autowired
	@Qualifier("respuestasRepository")
	RespuestasRepository respuestasRepository

	@Override
	def save(Respuestas respuestas) {
		return respuestasRepository.save(respuestas)
	}

	@Override
	def deleteRespuestasById(Integer id) {
		return respuestasRepository.deleteById(id)
	}

	@Override
	def getRespuestasById(Integer id) {
		Optional<Respuestas> respuesta = respuestasRepository.findById(id)
		return respuesta.isPresent() ? respuesta.get() : new Respuestas()
	}

	@Override
	def getRespuestasAll(Quiz idquiz, Integer idStudent) {
		List<Respuestas> respuestas = respuestasRepository.findAllByIdquiz(idquiz)
		return respuestas.findAll { it->it.getIdStudent().equals(idStudent) }
	}

	@Override
	def getAllRespuestasById(List<Integer> ids) {
		return respuestasRepository.findAllById(ids)
	}

}
