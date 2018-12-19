package com.quizwish.quiz.services

import com.quizwish.quiz.models.User
import org.springframework.data.domain.Example

interface UsuarioService {
	def getUsuarioAll();
	
	def getUsuarioById(Integer id);
				
	def save(User usuario, int rol);
			
	def deleteUsuario(Integer id);
	
	def getByCorreo(String correo);
			
	def getByCriteria(User user, int rol);
	
	def getByCriteriaList(Example<User> example);
	
	public User update(User user, User userSession);
	
	public User updateUser(User user);
	
	def findById(Integer id)
	
	def saveSimpleStudent(User user);
}
