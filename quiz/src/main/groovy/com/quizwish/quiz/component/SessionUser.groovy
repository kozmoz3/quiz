package com.quizwish.quiz.component

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import org.springframework.ui.Model

import com.quizwish.quiz.repositorys.UserRepository

@Component("sessionUser")
class SessionUser {

	private static final Log LOGGER = LogFactory.getLog(SessionUser.class)
	
	@Autowired
	@Qualifier("userRepository")
	UserRepository userRepository;
	
	public com.quizwish.quiz.models.User userSessionAddUsername(Model model) {
		LOGGER.info("METHOD : userSessionAddUsername ");
		com.quizwish.quiz.models.User user= userSessionAll()
		def username = user.getNombre();
		model.addAttribute("username", username);
		model.addAttribute("usernameimg", user.getPerfil());
		return user;
	}
	
	public String getNameSession() {
		return userSessionAll().getNombre();
	}
	
	public com.quizwish.quiz.models.User userSessionAll(){
		LOGGER.info("METHOD : userSessionAll ");
		User userSesion =  session();
        return userRepository.findByCorreo( userSesion.getUsername());
	}
	
	public User session() {
		return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	public boolean inSession() {
		Object sesion = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return sesion instanceof String ? false : true;
	}
}