package com.quizwish.quiz.services

import com.quizwish.quiz.models.Usuario
import org.springframework.data.domain.Example

interface UsuarioService {
	def getUsuarioAll();
	
	def getUsuarioById(Integer id);
				
	def setUsuario(Usuario Usuario);
			
	def deleteUsuario(Integer id);
			
	def getByCriteria(Example<Usuario> example);
	
	def getByCriteriaList(Example<Usuario> example);
}
