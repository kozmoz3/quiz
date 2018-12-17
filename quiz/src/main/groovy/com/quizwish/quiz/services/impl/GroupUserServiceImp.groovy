package com.quizwish.quiz.services.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

import com.quizwish.quiz.component.GrupoUserComponent
import com.quizwish.quiz.entity.Grupo
import com.quizwish.quiz.entity.Grupousuario
import com.quizwish.quiz.models.User
import com.quizwish.quiz.repositorys.GroupUserRepository
import com.quizwish.quiz.services.GroupUserService

@Service("grupouserService")
class GroupUserServiceImp implements GroupUserService{
	
	@Autowired
	@Qualifier("groupuserRepository")
	GroupUserRepository groupuserRepository
	
	@Autowired
	@Qualifier("grupouserComponent")
	GrupoUserComponent grupouserComponent
	
	@Override
	def getGroupUserAll() {
		return groupuserRepository.findAll()
	}

	@Override
	def getGroupUserById(Integer id) {
		Optional<Grupousuario> grupou = groupuserRepository.findById(id)
		return grupou.isPresent() ? grupou.get() : new Grupousuario()
	}

	@Override
	def deleteGroupUser(Integer id) {
		return groupuserRepository.deleteById(id)
	}

	@Override
	public Object setGrupoUser(Grupo grupo, List<Grupousuario> grupousuario, User user) {
		List<Grupousuario> addgrupou = grupouserComponent.setGrupoUser(grupo, grupousuario, user)
		return groupuserRepository.saveAll(addgrupou)
	}

	@Override
	public Grupousuario findAllByIdStudent(Integer student) {
		return groupuserRepository.findByIdstudent(student)
	}
}
