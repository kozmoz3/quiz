package com.quizwish.quiz.controllers

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

import com.quizwish.quiz.component.SessionUser
import com.quizwish.quiz.models.User
import com.quizwish.quiz.services.GroupUserService

@Controller
@RequestMapping(path = "/me")
class StudentController {
	
	private static final Log LOGGER = LogFactory.getLog(StudentController.class)
	
	static final def REALIZE ="student/components/quiz/realize"
	static final def RESULT ="student/components/resultados/list"
	static final def RESPONSE ="student/components/resultados/view-result"
	static final def PROFILE ="student/components/me/perfil"
	static final def INDEX ="student/index"
	
	@Autowired
	@Qualifier("sessionUser")
	SessionUser sessionUser;
	
	@Autowired
	@Qualifier("grupouserService")
	GroupUserService grupouserService
	
	@GetMapping("/")
	def index(Model model) {
		LOGGER.info("Method: -- index")
		User user = sessionUser.userSessionAddUsername(model);
		model.addAttribute("lstgrupos", grupouserService.findAllByIdStudent(user.iduser));
		model.addAttribute("usuario", user);
		return INDEX
	}
	
	@GetMapping("/realize")
	def realize(Model model) {
		LOGGER.info("Method: -- realize")
		return REALIZE;
	}
	
	@GetMapping("/result")
	def result(Model model) {
		LOGGER.info("Method: -- result")
		return RESULT;
	}
	
	@GetMapping("/response")
	def response(Model model) {
		LOGGER.info("Method: -- response")
		return RESPONSE;
	}
	
	@GetMapping("/profile")
	def profile(Model model) {
		LOGGER.info("Method: -- profile")
		User user = sessionUser.userSessionAddUsername(model);
		model.addAttribute("usuario", user);
		return PROFILE;
	}
	
	@PutMapping("/profile/personal/edit")
	//@RequestMapping(value = "/sendcontact", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	public User personal(@ModelAttribute("personal") User personal, Model model) {
		User user = sessionUser.userSessionAddUsername(model);
		user.setNombre(personal.nombre)
		user.setApellidos(personal.apellidos)
		user.setTelefono(personal.telefono)
		user.setUsername(personal.username)
		return user
	}
	
}
