package com.quizwish.quiz.services.impl

import com.quizwish.quiz.models.Usuario
import com.quizwish.quiz.repositorys.UsuarioRepository
import com.quizwish.quiz.services.UsuarioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.domain.Example
import org.springframework.stereotype.Service

@Service("serviceUsuario")
class UsuarioServiceImp implements UsuarioService{
	
	@Autowired
	@Qualifier("usuarioRepository")
	UsuarioRepository usuarioRepository;

	@Override
	def getUsuarioAll() {
		return usuarioRepository.findAll()
	}

	@Override
	def getUsuarioById(Integer id) {
		return usuarioRepository.findById(id)
	}

	@Override
	def setUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario)
	}

	@Override
	def deleteUsuario(Integer id) {
		return usuarioRepository.deleteById(id)
	}

	@Override
	def getByCriteria(Example<Usuario> example) {
		return usuarioRepository.findOne(example)
	}

	@Override
	def getByCriteriaList(Example<Usuario> example) {
		return usuarioRepository.findAll(example)
	}

}
