package com.quizwish.quiz.controllers

import org.apache.commons.logging.Log
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

import com.quizwish.quiz.sesion.AppUserDAO

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

@Controller
@RequestMapping(path = "/")
class LoginController {
	
	private static final Log LOGGER = LogFactory.getLog(QuizController.class)
	
	static final def INDEX = "login";
	static final def ADMIN = "admin/index";
	
	@Autowired
	@Qualifier("appUserDAO")
	AppUserDAO appUserDAO
	
	@GetMapping("/")
	public String login(Model model) {
		LOGGER.info("login user "+appUserDAO.findUserAccount("Alfonso"));
		return INDEX;
	}
	
	@GetMapping("/admin")
	public String index(Model model) {
		return ADMIN;
	}

}
