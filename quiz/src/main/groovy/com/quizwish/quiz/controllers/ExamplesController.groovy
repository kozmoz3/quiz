package com.quizwish.quiz.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping(path = "/")
class ExamplesController {
	
	@GetMapping("/")
	public String index(Model model) {
		return "admin/index";
	}
	
	@GetMapping("/simuladores")
	public String simuladores(Model model) {
		return "admin/components/simuladores/list";
	}

	@GetMapping("/simuladores/add")
	public String simuladoresAdd(Model model) {
		return "admin/components/simuladores/crud";
	}
	
	@GetMapping("/simuladores/preguntas/add")
	public String simuladoresPreguntasAdd(Model model) {
		return "admin/components/simuladores/questions";
	}
	
	@GetMapping("/simuladores/preguntas")
	public String simuladoresPreguntas(Model model) {
		return "admin/components/simuladores/list-preguntas";
	}
	
	@GetMapping("/estudiantes")
	public String estudiantes(Model model) {
		return "admin/components/estudiantes/list";
	}
	
	@GetMapping("/estudiantes/add")
	public String estudiantesAdd(Model model) {
		return "admin/components/estudiantes/crud";
	}
	
	@GetMapping("/me")
	public String me(Model model) {
		return "admin/components/me/index";
	}
	
	@GetMapping("/grupos")
	public String grupos(Model model) {
		return "admin/components/grupos/list";
	}
	
	@GetMapping("/grupos/add")
	public String gruposAdd(Model model) {
		return "admin/components/grupos/crud";
	}
	
}
