package com.quizwish.quiz.controllers

import com.quizwish.quiz.models.User
import com.quizwish.quiz.services.RolService
import com.quizwish.quiz.services.UsuarioService

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class RegisterController {
	
	private static final Log LOGGER = LogFactory.getLog(RegisterController.class)
	
	@Autowired
	@Qualifier("usuarioService")
	UsuarioService usuarioService
	
	@Autowired
	@Qualifier("rolService")
	RolService rolService
	
	static final def INDEX = "register";
	
	@GetMapping("/register")
	public String showRegisterForm(Model model ) {
		LOGGER.info("METHOD : showRegisterForm --");
		model.addAttribute("type", "show" )
		return INDEX;
	}
	
	@PostMapping("/register")
	def newUserAdmin(@ModelAttribute("user") User usuario, Model model) {
		def encode = new BCryptPasswordEncoder()
		usuario.setPassword( encode.encode( usuario.getPassword() ) )
		usuario.setIdrol( rolService.getRolById(1) )
		usuario.setEnable(true)
		User user = usuarioService.setUsuario(usuario)
		
		model.addAttribute("type", "post" )
		model.addAttribute("estatus", "ok" )
		model.addAttribute("newuser", user.getIduser() != null ? user : new User() )
		return INDEX;
	}
}
