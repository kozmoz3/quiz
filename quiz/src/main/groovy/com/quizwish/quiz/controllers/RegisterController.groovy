package com.quizwish.quiz.controllers

import com.quizwish.quiz.models.User
import com.quizwish.quiz.services.ContratoService
import com.quizwish.quiz.services.RolService
import com.quizwish.quiz.services.UsuarioService

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.domain.Example
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
		
	static final def INDEX = "register";
	
	@GetMapping("/register")
	public String showRegisterForm(Model model ) {
		LOGGER.info("METHOD : showRegisterForm --");
		model.addAttribute("type", "show" )
		return INDEX;
	}
	
	@PostMapping("/register")
	def newUserAdmin(@ModelAttribute("user") User usuario, Model model) {
		int rol = 1;
		User userRepeat = usuarioService.getByCorreo(usuario.correo)
		//LOGGER.info("isEmailRepeat => " + userRepeat.iduser)
		if( userRepeat != null ) 
			model.addAttribute("estatus", "correo" )
		else {
			User user = usuarioService.register(usuario, rol)
			LOGGER.info("METHOD : newUserAdmin --" + user.getNombre());
			model.addAttribute("estatus", "ok" )
			model.addAttribute("newuser", user.getIduser() != null ? user : new User() )
		}
		model.addAttribute("type", "post" )
		return INDEX;
	}
}
