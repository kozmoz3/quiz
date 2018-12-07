package com.quizwish.quiz.services

import com.quizwish.quiz.models.User
import org.springframework.data.domain.Example

interface UsuarioService {
	def getUsuarioAll();
	
	def getUsuarioById(Integer id);
				
	def setUsuario(User Usuario);
			
	def deleteUsuario(Integer id);
			
	def getByCriteria(Example<User> example);
	
	def getByCriteriaList(Example<User> example);
}
