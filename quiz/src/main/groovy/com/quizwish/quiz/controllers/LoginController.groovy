package com.quizwish.quiz.controllers

import static org.assertj.core.api.Assertions.filter

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import com.quizwish.quiz.component.ContratosComponent
import com.quizwish.quiz.component.SessionUser;
import com.quizwish.quiz.entity.Contrato
import com.quizwish.quiz.models.User
import com.quizwish.quiz.services.GroupUserService
import com.quizwish.quiz.services.impl.StartAdminService


@Controller
class LoginController {
	
	private static final Log LOGGER = LogFactory.getLog(LoginController.class)
	
	static final def INDEX = "login";
	static final def STUDENT = "student/index";
	static final def ADMIN = "admin/index";
	static final def FINSUBSCRIPCION = "terminada";
	
	@Autowired
	@Qualifier("sessionUser")
	SessionUser sessionUser;
	
	@Autowired
	@Qualifier("startAdminService")
	StartAdminService startAdminService
	
	@Autowired
	@Qualifier("grupouserService")
	GroupUserService grupouserService
	
	@Autowired
	@Qualifier("contratoscomponent")
	ContratosComponent contratoscomponent
	
	@GetMapping("/login")
	public String showLoginForm(Model model,
		                        @RequestParam(name="error", defaultValue="", required=false)String error ) {
		LOGGER.info("METHOD : showLoginForm --");
		model.addAttribute("error", error)
		
		return INDEX;
	}
	
	@PreAuthorize("hasRole('ROLE_ROOT') or hasRole('ROLE_ALUM') ")
	@GetMapping("/loginsucces")
	public String loginCheck(Model model) {
		User user = sessionUser.userSessionAddUsername(model);
		LOGGER.info("METHOD : loginCheck --  session "+ user.getRol());
		if(!contratoscomponent.hasAny(user.contratoList)) {
			return FINSUBSCRIPCION;
		}
		if(user.getIdrol() == 1) {
			int numStudent = startAdminService.countStudentByTeacher(user.getIduser());
			int numQuiz = startAdminService.countQuizByTeacher(user);
			model.addAttribute("numStudent", numStudent);
			model.addAttribute("numQuiz", numQuiz);
		return ADMIN;
		}else {
			model.addAttribute("lstgrupos", grupouserService.findAllByIdStudent(user.iduser));
			model.addAttribute("usuario", user);
			return STUDENT;
		}
	}

}
