package com.quizwish.quiz.controllers

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class RegisterController {
	
	private static final Log LOGGER = LogFactory.getLog(RegisterController.class)
	
	static final def INDEX = "register";
	
	@GetMapping("/register")
	public String showLoginForm(Model model ) {
		LOGGER.info("METHOD : showRegisterForm --");		
		return INDEX;
	}
}
