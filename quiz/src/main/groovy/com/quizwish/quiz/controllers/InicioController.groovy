package com.quizwish.quiz.controllers

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class InicioController {
	
	private static final Log LOGGER = LogFactory.getLog(InicioController.class)
	
	static final def INDEX = "index";
	
	@GetMapping("/")
	public String showIndex(Model model ) {
		LOGGER.info("METHOD : showIndex --");		
		return INDEX;
	}
}
