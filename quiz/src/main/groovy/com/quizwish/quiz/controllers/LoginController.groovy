package com.quizwish.quiz.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping(path = "/")
class LoginController {
	
	static final def INDEX = "login";
	static final def ADMIN = "admin/index";
	
	@GetMapping("/")
	public String login(Model model) {
		return INDEX;
	}
	
	@GetMapping("/admin")
	public String index(Model model) {
		return ADMIN;
	}

}
