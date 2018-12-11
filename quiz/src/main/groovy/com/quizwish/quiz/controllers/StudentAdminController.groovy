package com.quizwish.quiz.controllers

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping(path = "/admin")
@Controller
class StudentAdminController {
	
	private static final Log LOGGER = LogFactory.getLog(StudentAdminController.class)

	@GetMapping("/estudiantes")
	def show(Model model) {
		LOGGER.info("METHOD : show");
		return "admin/components/estudiantes/list";
	}
}
