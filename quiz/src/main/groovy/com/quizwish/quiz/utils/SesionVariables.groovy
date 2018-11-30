package com.quizwish.quiz.utils

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails

class SesionVariables {

	public static UserDetails sesionDetails() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal()
		return (principal instanceof UserDetails) ? (UserDetails) principal : null
	}
}