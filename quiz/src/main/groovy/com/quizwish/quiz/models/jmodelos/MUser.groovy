package com.quizwish.quiz.models.jmodelos

import org.hibernate.validator.constraints.Length

class MUser {
		
	Integer iduser
	String nombre
	String apellidos
	String telefono
	String correo
	String username
	String password
	String perfil
	boolean enable
	String types
	@Override
	public String toString() {
		return "MUser [iduser=" + iduser + ", nombre=" + nombre + ", apellidos=" + apellidos + ", telefono=" + telefono + ", correo=" + correo + ", username=" + username + ", password=" + password + ", perfil=" + perfil + ", enable=" + enable + "]";
	}

	
}
