package com.quizwish.quiz.controllers

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import com.quizwish.quiz.component.SessionUser
import com.quizwish.quiz.models.User
import com.quizwish.quiz.models.jmodelos.MGrupoUser
import com.quizwish.quiz.models.jmodelos.MUser
import com.quizwish.quiz.services.GroupUserService
import com.quizwish.quiz.services.StudentService

@Controller
@RequestMapping(path = "/me")
class StudentController {
	
	private static final Log LOGGER = LogFactory.getLog(StudentController.class)
	
	static final def REALIZE ="student/components/quiz/realize"
	static final def RESULT ="student/components/resultados/list"
	static final def RESPONSE ="student/components/resultados/view-result"
	static final def PROFILE ="student/components/me/perfil"
	static final def REPROFILE ="redirect:/me/profile"
	static final def INDEX ="student/index"
	static final def RESPONSEAll ="response"
	
	@Autowired
	@Qualifier("sessionUser")
	SessionUser sessionUser;
	
	@Autowired
	@Qualifier("grupouserService")
	GroupUserService grupouserService
	
	@Autowired
	@Qualifier("studentService")
	StudentService studentService
	
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

	@PreAuthorize("hasRole('ROLE_ALUM')")
	@RequestMapping(value = "/profile/personal/edit", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	def personal(@RequestBody MUser personal, Model model) {
		LOGGER.info("Method: -- personal " + personal.toString())
		User user = sessionUser.userSessionAddUsername(model);
		studentService.savePersonal(user, personal)
		return RESPONSEAll
	}
	
	@PutMapping("/profile/img/edit")
	def uploadFile(@RequestParam("perfil") MultipartFile perfil, RedirectAttributes redirectAttributes, Model model){
		User user = sessionUser.userSessionAddUsername(model);
		model.addAttribute("usuario", user);
		if(!perfil.isEmpty()) {
			LOGGER.info("Method: -- personal => Tamaño => " + perfil.getSize() +" Nombre: " + perfil.getName() +" Original: " + perfil.getOriginalFilename() );
			try {
				studentService.saveProfile(user, perfil)
			} catch(Exception e) {
								
			}
		}
		return PROFILE
	}
	
}
