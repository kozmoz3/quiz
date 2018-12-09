package com.quizwish.quiz.utils

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails

import com.quizwish.quiz.models.Usuario
//import com.quizwish.quiz.services.UsuarioService


class SesionVariables {

	/*@Autowired
	@Qualifier("serviceUsuario")
	static UsuarioService serviceUsuario

	public static UserDetails sesionDetails() {
		Authentication authentication = getAuthentication()
		Object principal = authentication.getPrincipal()
		return (principal instanceof UserDetails) ? (UserDetails) principal : null
	}

	public static Integer getUserId() {
		Authentication authentication = getAuthentication()
		String id = null
		if (authentication != null)
			if (authentication.getPrincipal() instanceof UserDetails)
				id = ((UserDetails) authentication.getPrincipal()).getUsername()
			else if (authentication.getPrincipal() instanceof String)
				id = (String) authentication.getPrincipal()
		try {
			return Integer.valueOf(id != null ? id : "0")
		} catch (NumberFormatException e) {
			return 1L;
		}
	}

	public static Usuario getUser() {
		Optional<Usuario> opUsuario = (Optional<Usuario>) serviceUsuario.getUsuarioById( getUserId() )
		return opUsuario.isPresent() ? opUsuario.get() : new Usuario()
	}

	private static Authentication getAuthentication() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		return securityContext.getAuthentication();
	}*/
}
