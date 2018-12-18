package com.quizwish.quiz.services

import com.quizwish.quiz.entity.Grupo
import com.quizwish.quiz.entity.Grupousuario
import com.quizwish.quiz.models.User
import com.quizwish.quiz.models.jmodelos.MGrupoUser

interface GroupUserService {
	
	def setGrupoUser(Grupo grupo, List<Grupousuario> grupousuario, User user);
	
	def setGrupoUserOnlyOne(Grupo grupo, MGrupoUser grupousuario, User user);
	
	def getGroupUserAll();
	
	def getGroupUserById(Integer id);
	
	def deleteGroupUser(Integer id);
	
	public abstract Grupousuario findAllByIdStudent(Integer student);
}
