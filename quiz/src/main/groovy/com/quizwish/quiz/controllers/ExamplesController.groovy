package com.quizwish.quiz.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping(path = "/")
class ExamplesController {
	
	@GetMapping("/")
	public String index(Model model) {
		return "index";
	}

	@GetMapping("/quiz")
	public String quiz(Model model) {
		return "quiz";
	}
	
}
