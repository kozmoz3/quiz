package com.quizwish.quiz.controllers

import static org.assertj.core.api.Assertions.useRepresentation

import java.util.function.Predicate

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

import com.quizwish.quiz.entity.Quiz
import com.quizwish.quiz.models.User
import com.quizwish.quiz.services.UsuarioService

@Controller
@RequestMapping(path = "/")
class ExamplesController {
	
	@Autowired
	@Qualifier("usuarioService")
	UsuarioService usuarioService
		
	@GetMapping("/admin/simuladores/preguntas")
	def simuladoresPreguntas(Model model) {
		return "admin/components/simuladores/list-preguntas";
	}
	
	
	
	
	
	@GetMapping("/admin/me")
	def me(Model model) {
		return "admin/components/me/index";
	}
	
}
