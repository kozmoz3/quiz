package com.quizwish.quiz.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping(path = "/")
class LoginController {
	
	static final def INDEX = "login";
	
	@GetMapping("/")
	public String index(Model model) {
		return INDEX;
	}

}
