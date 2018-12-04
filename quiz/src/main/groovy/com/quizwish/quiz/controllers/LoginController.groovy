package com.quizwish.quiz.controllers

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam


@Controller
class LoginController {
	
	private static final Log LOGGER = LogFactory.getLog(QuizController.class)
	
	static final def INDEX = "login";
	static final def ADMIN = "admin/index";
	
	
	@GetMapping("/login")
	public String showLoginForm(Model model,
		                        @RequestParam(name="error", defaultValue="", required=false)String error ) {
		LOGGER.info("METHOD : showLoginForm --");
		model.addAttribute("error", error)
		
		return INDEX;
	}
	
	@GetMapping("/loginsucces")
	public String loginCheck() {
		LOGGER.info("METHOD : loginCheck -- ");
		return ADMIN;
	}

}
