package com.quizwish.quiz.component

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import org.springframework.ui.Model

import com.quizwish.quiz.repositorys.UserRepository

@Component("sessionUser")
class SessionUser {

	@Autowired
	@Qualifier("userRepository")
	UserRepository userRepository;
	
	public com.quizwish.quiz.models.User userSessionAddUsername(Model model) {
		com.quizwish.quiz.models.User user= userSessionAll()
		def username = user.getNombre();
		model.addAttribute("username", username);
		return user;
	}
	
	public String getNameSession() {
		return userSessionAll().getNombre();
	}
	
	public com.quizwish.quiz.models.User userSessionAll(){
		User userSesion =  session();
        return userRepository.findByCorreo( userSesion.getUsername());
	}
	
	public User session() {
		return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}