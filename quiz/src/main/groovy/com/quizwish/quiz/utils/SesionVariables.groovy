package com.quizwish.quiz.utils

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import com.quizwish.quiz.models.Usuario
import com.quizwish.quiz.services.UsuarioService

class SesionVariables {
	
	@Autowired
	@Qualifier("serviceUsuario")
	static UsuarioService serviceUsuario

	public static UserDetails sesionDetails() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal()
		return (principal instanceof UserDetails) ? (UserDetails) principal : null
	}
	
	public static Usuario getUserlog() {
		UserDetails details = this.sesionDetails()
		ToExampleQuery<Usuario> example = new ToExampleQuery<>(new Usuario(), details.getUsername() )
		return serviceUsuario.getByCriteria(example);
	}
}
