package com.quizwish.quiz.controllers

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

import com.quizwish.quiz.component.SessionUser
import com.quizwish.quiz.models.User

@Controller
@RequestMapping(path = "/admin")
class UserController {
     
	private static final Log LOGGER = LogFactory.getLog(UserController.class)
	static final def SHOW = "admin/components/me/index";
	
	@Autowired
	@Qualifier("sessionUser")
	SessionUser sessionUser;
	
	@PreAuthorize("hasRole('ROLE_ROOT')")
	@GetMapping("/user")
	def show(Model model) {
		LOGGER.info("METHOD : show ");
		User user = sessionUser.userSessionAddUsername(model);	
		model.addAttribute("user", user);
		return SHOW;
	}
	
	@PreAuthorize("hasRole('ROLE_ROOT')")
	@PostMapping("/user")
	def post(Model model) {
		User user = sessionUser.userSessionAll();
		def username = user.getNombre();
		model.addAttribute("username", username);
		model.addAttribute("user", user);
		return SHOW;
	}
}
