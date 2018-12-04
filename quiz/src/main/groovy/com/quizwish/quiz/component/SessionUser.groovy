package com.quizwish.quiz.component

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component

import com.quizwish.quiz.repositorys.UserRepository

@Component("sessionUser")
class SessionUser {

	@Autowired
	@Qualifier("userRepository")
	UserRepository userRepository;
	
	public com.quizwish.quiz.models.User userSessionAll(){
		User userSesion =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByCorreo( userSesion.getUsername());
	}
}