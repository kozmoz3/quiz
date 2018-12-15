package com.quizwish.quiz.services.impl


import com.quizwish.quiz.entity.Grupo
import com.quizwish.quiz.entity.Grupousuario
import com.quizwish.quiz.models.User
import com.quizwish.quiz.repositorys.GroupRepository
import com.quizwish.quiz.repositorys.GroupUserRepository
import com.quizwish.quiz.services.GroupService
import com.quizwish.quiz.services.StudentService
import com.quizwish.quiz.services.UsuarioService
import com.quizwish.quiz.utils.StatusTrueUtil

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service("grupoService")
class GroupServiceImp implements GroupService{
	@Autowired
	@Qualifier("grupoRepository")
	GroupRepository grupoRepository
	
	@Autowired
	@Qualifier("groupuserRepository")
	GroupUserRepository groupuserRepository
	
	@Autowired
	@Qualifier("studentService")
	StudentService studentService
	
	@Override
	public List<User> getStudentAllByUserId(User userAdmin){
		return studentService.findAllStudent(userAdmin.getIduser())
	} 

	@Override
	public List<Grupo> getGroupAllByUser(User user){
		return StatusTrueUtil.groupWithStatusTrue( user.getGrupList());
	}
	
	@Override
	def getGroupAll() {
		return grupoRepository.findAll()
	}

	@Override
	def getGroupById(Integer id) {
		Optional<Grupo> grupo = grupoRepository.findById(id)
		return grupo.isPresent() ? grupo : new Grupo()
	}

	@Override
	public Grupousuario setGroup(Grupo grupo, Grupousuario grupousuario, User user) {
		Grupo grupoadd = grupoRepository.save(grupo)
		Grupousuario grupousuarioadd = setGrupoUser(grupoadd, grupousuario, user)
		return null;
	}
	
	private Grupousuario setGrupoUser(Grupo grupo, Grupousuario grupousuario, User user) {
		if(grupo.getIdgrupo() != null) {
			
		}
	}

	@Override
	def deleteGroup(Integer id) {
		return grupoRepository.deleteById(id)
	}

	@Override
	def getGroupUserAll() {
		return groupuserRepository.findAll()
	}

	@Override
	def getGroupUserById(Integer id) {
		return groupuserRepository.findById(id)
	}

	@Override
	def deleteGroupUser(Integer id) {
		return groupuserRepository.deleteById(id)
	}

}
