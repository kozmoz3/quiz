package com.quizwish.quiz.utils

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

import com.quizwish.quiz.sesion.AppUser


class SesionVariables {

	public static def sesionDetails() {
		 AppUser user = (AppUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()
return user;
		 	//	return (principal instanceof UserDetails) ? (UserDetails) principal : null
	}
}
