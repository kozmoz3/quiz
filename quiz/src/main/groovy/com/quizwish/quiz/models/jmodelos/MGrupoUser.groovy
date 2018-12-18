package com.quizwish.quiz.models.jmodelos

import com.quizwish.quiz.entity.Grupousuario

class MGrupoUser {
	List<Grupousuario> grupousuario
	int idgrupo
	@Override
	public String toString() {
		return "MGrupoUser [grupousuario=" + grupousuario + ", idgrupo=" + idgrupo + "]";
	}
}
