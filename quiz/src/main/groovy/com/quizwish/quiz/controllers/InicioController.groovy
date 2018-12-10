package com.quizwish.quiz.controllers

import com.quizwish.quiz.models.jmodelos.MContacto
import com.quizwish.quiz.services.EmailService

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class InicioController {
	
	private static final Log LOGGER = LogFactory.getLog(InicioController.class)
	
	static final def INDEX = "index";
	
	@Autowired
	@Qualifier("servicioMails")
	EmailService servicioMails;
	
	@GetMapping("/")
	public String showIndex(Model model ) {
		LOGGER.info("METHOD : showIndex --");		
		return INDEX;
	}
	
	@PostMapping("/sendcontact")
	public void sendEmail( @RequestBody MContacto contacto ) {
		//LOGGER.info("Datos recibidos => " + contacto.name + " " + contacto.email + " " + contacto.message)
		servicioMails.sendMail("alvaco_1993@hotmail.com", contacto.name + " > " + contacto.email, contacto.message)
	}
}
