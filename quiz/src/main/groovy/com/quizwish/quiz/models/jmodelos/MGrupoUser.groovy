package com.quizwish.quiz.models.jmodelos

import com.quizwish.quiz.entity.Grupousuario

class MGrupoUser {
	List<Grupousuario> grupousuario
	int idgrupo
	
	// Only one
	int idstudent
	boolean status
	@Override
	public String toString() {
		return "MGrupoUser [grupousuario=" + grupousuario + ", idgrupo=" + idgrupo + "]";
	}
}
