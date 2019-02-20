package com.quizwish.quiz.controllers

import com.quizwish.quiz.component.SessionUser
import com.quizwish.quiz.models.User
import com.quizwish.quiz.models.jmodelos.MContacto
import com.quizwish.quiz.services.EmailService

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.MediaType
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

import org.springframework.web.bind.annotation.RequestMethod

@Controller
class InicioController {
	
	private static final Log LOGGER = LogFactory.getLog(InicioController.class)
	
	static final def INDEX = "index";
	static final def DETAILS = "details";
	
	@Autowired
	@Qualifier("servicioMails")
	EmailService servicioMails;
	
	@Autowired
	@Qualifier("sessionUser")
	SessionUser sessionUser;
	
	@GetMapping("/")
	public String showIndex(Model model ) {
		LOGGER.info("METHOD : showIndex --");		
		return INDEX;
	}
		
	@RequestMapping(value = "/sendcontact", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	def sendEmail( @RequestBody MContacto contacto ) {
		servicioMails.sendMail("alvaco_1993@hotmail.com", contacto.name + " > " + contacto.email, contacto.message)
		return INDEX;
	}
	
	@RequestMapping(value="/details", method = RequestMethod.GET)
	def details(@AuthenticationPrincipal User principal, Model model) {
		model.addAttribute("userlog", principal);
		return DETAILS
	}
}
