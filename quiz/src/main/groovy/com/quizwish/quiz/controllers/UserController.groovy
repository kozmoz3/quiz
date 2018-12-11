package com.quizwish.quiz.controllers

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Service
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

import com.quizwish.quiz.component.SessionUser
import com.quizwish.quiz.models.User
import com.quizwish.quiz.services.UsuarioService

@Controller
@RequestMapping(path = "/admin")
class UserController {
     
	private static final Log LOGGER = LogFactory.getLog(UserController.class)
	static final def SHOW = "admin/components/me/index";
	
	@Autowired
	@Qualifier("sessionUser")
	SessionUser sessionUser;
	
	@Autowired
	@Qualifier("usuarioService")
	UsuarioService usuarioService
	
	@PreAuthorize("hasRole('ROLE_ROOT')")
	@GetMapping("/user")
	def show(Model model) {
		LOGGER.info("METHOD : show ");
		User users = sessionUser.userSessionAddUsername(model);	
		model.addAttribute("users", users);
		return SHOW;
	}
	
	@PreAuthorize("hasRole('ROLE_ROOT')")
	@PostMapping("/users")
	public String update(@ModelAttribute("users")User users,Model model) {
		LOGGER.info("METHOD : show --- Param "+ users.toString());
		User user = sessionUser.userSessionAddUsername(model);
		usuarioService.save(users, user)
		model.addAttribute("user", user);
		return SHOW;
	}
}
