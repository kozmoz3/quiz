package com.quizwish.quiz.controllers

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import com.quizwish.quiz.component.SessionUser;


@Controller
class LoginController {
	
	private static final Log LOGGER = LogFactory.getLog(QuizController.class)
	
	static final def INDEX = "login";
	static final def ADMIN = "admin/index";
	
	@Autowired
	@Qualifier("sessionUser")
	SessionUser sessionUser;
	
	@GetMapping("/login")
	public String showLoginForm(Model model,
		                        @RequestParam(name="error", defaultValue="", required=false)String error ) {
		LOGGER.info("METHOD : showLoginForm --");
		model.addAttribute("error", error)
		
		return INDEX;
	}
	
	@PreAuthorize("hasRole('ROLE_ROOT') or hasRole('ROLE_ALUM') ")
	@GetMapping("/loginsucces")
	public String loginCheck() {
		LOGGER.info("METHOD : loginCheck --  session "+sessionUser.userSessionAll().getNombre() );
		return ADMIN;
	}

}
