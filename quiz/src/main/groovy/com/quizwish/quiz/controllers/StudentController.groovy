package com.quizwish.quiz.controllers

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping(path = "/me")
class StudentController {
	
	private static final Log LOGGER = LogFactory.getLog(StudentController.class)
	
	static final def REALIZE ="student/components/quiz/realize"
	static final def RESULT ="student/components/resultados/list"
	static final def RESPONSE ="student/components/resultados/view-result"
	static final def PROFILE ="student/components/me/perfil"
	static final def INDEX ="student/index"
	
	@GetMapping("/")
	public String index(Model model) {
		LOGGER.info("Method: -- index")
		return INDEX;
	}
	
	@GetMapping("/realize")
	public String realize(Model model) {
		LOGGER.info("Method: -- realize")
		return REALIZE;
	}
	
	@GetMapping("/result")
	public String result(Model model) {
		LOGGER.info("Method: -- result")
		return RESULT;
	}
	
	@GetMapping("/response")
	public String response(Model model) {
		LOGGER.info("Method: -- response")
		return RESPONSE;
	}
	
	@GetMapping("/profile")
	public String profile(Model model) {
		LOGGER.info("Method: -- profile")
		return PROFILE;
	}
	
}
