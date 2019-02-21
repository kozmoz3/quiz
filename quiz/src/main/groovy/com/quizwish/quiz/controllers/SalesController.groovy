package com.quizwish.quiz.controllers

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

import com.quizwish.quiz.component.SessionUser

@Controller
@RequestMapping(path = "/admin")
class SalesController {
	
	private static final Log LOGGER = LogFactory.getLog(SalesController.class)
	static final def LSTINDEX = "admin/components/compras/index";
	
	@Autowired
	@Qualifier("sessionUser")
	SessionUser sessionUser;
	
	@GetMapping("/compras")
	def simuladoresPreguntas(Model model) {
		model.addAttribute("compras", sessionUser.userSessionAll().contratoList)
		return LSTINDEX
	}

}
