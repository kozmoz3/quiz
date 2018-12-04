package com.quizwish.quiz.controllers

import org.apache.commons.logging.Log
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

import org.springframework.security.core.userdetails.UserDetails
import com.quizwish.quiz.utils.SesionVariables

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

@Controller
//@RequestMapping(path = "/")
class LoginController {
	
	private static final Log LOGGER = LogFactory.getLog(QuizController.class)
	
	static final def INDEX = "login";
	static final def ADMIN = "admin/index";
	
	
	@GetMapping("login")
	public String login(Model model) {
		return INDEX;
	}
	
	@PostMapping("/loginsucces")
	public String index(Model model) {
		//UserDetails details = SesionVariables.sesionDetails()
			LOGGER.info("login user - "+ SesionVariables.sesionDetails());
		//	model.addAttribute("usuario", details.getUsername())
		return ADMIN;
	}

}
