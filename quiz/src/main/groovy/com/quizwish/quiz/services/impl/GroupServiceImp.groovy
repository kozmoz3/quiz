package com.quizwish.quiz.services.impl

import com.quizwish.quiz.component.SessionUser
import com.quizwish.quiz.entity.Grupo
import com.quizwish.quiz.entity.Grupousuario
import com.quizwish.quiz.entity.Student
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
	@Qualifier("studentService")
	StudentService studentService
	
	@Override
	public Grupo save(Grupo grupo) {
		return grupoRepository.save(grupo)
	}
	
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
		return grupo.isPresent() ? grupo.get() : new Grupo()
	}

	@Override
	def setGroup(Grupo grupo, User usuario) {
		grupo.setIduser(usuario)
		return grupoRepository.save(grupo)
	}
	
	@Override
	def deleteGroup(Integer id) {
		return grupoRepository.deleteById(id)
	}

}
