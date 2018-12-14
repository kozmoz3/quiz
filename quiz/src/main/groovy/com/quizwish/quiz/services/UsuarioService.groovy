package com.quizwish.quiz.services

import com.quizwish.quiz.models.User
import org.springframework.data.domain.Example

interface UsuarioService {
	def getUsuarioAll();
	
	def getUsuarioById(Integer id);
				
	def save(User Usuario, int rol);
			
	def deleteUsuario(Integer id);
	
	def getByCorreo(String correo);
			
	def getByCriteria(User user, int rol);
	
	def getByCriteriaList(Example<User> example);
	
	public User update(User user, User userSession);
	
	def findById(int id)
}
