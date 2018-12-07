package com.quizwish.quiz.services.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import com.quizwish.quiz.models.Rol
import com.quizwish.quiz.repositorys.RolRepository
import com.quizwish.quiz.services.RolService

@Service("rolService")
class RolServiceImp implements RolService{
	
	@Autowired
	@Qualifier("rolRepository")
	RolRepository rolRepository

	@Override
	def getRolById(Integer id) {
		Optional<Rol> rol =  rolRepository.findById(id)
		return rol.isPresent() ? rol.get() : new Rol()
	}
}
