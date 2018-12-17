package com.quizwish.quiz.services


import org.springframework.data.domain.Example
import com.quizwish.quiz.entity.Grupo
import com.quizwish.quiz.entity.Grupousuario
import com.quizwish.quiz.models.User

interface GroupService {
	
	public Grupo save(Grupo grupo);
	
	public List<User> getStudentAllByUserId(User userAdmin)
	
	public List<Grupo> getGroupAllByUser(User user)
	//Group
	def getGroupAll();
	
	def getGroupById(Integer id);
				
	def setGroup(Grupo grupo, User usuario);
			
	def deleteGroup(Integer id);

}
