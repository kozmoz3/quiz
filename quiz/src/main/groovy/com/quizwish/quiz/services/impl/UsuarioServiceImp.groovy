package com.quizwish.quiz.services.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.domain.Example
import org.springframework.stereotype.Service

import com.quizwish.quiz.models.User
import com.quizwish.quiz.repositorys.UserRepository
import com.quizwish.quiz.services.UsuarioService

@Service("usuarioService")
class UsuarioServiceImp implements UsuarioService{
	
	@Autowired
	@Qualifier("userRepository")
	UserRepository userRepository

	@Override
	def getUsuarioAll() {
		return userRepository.findAll()
	}

	@Override
	def getUsuarioById(Integer id) {
		Optional<User> usuario = userRepository.findById(id)
		return usuario.isPresent() ? usuario.get() : new User()
	}

	@Override
	def setUsuario(User usuario) {
		return userRepository.save(usuario)
	}

	@Override
	def deleteUsuario(Integer id) {
		return userRepository.deleteById(id)
	}

	@Override
	public Object getByCriteria(Example<User> example) {
		return null;
	}

	@Override
	public Object getByCriteriaList(Example<User> example) {
		return null;
	}
}
