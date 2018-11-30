package com.quizwish.quiz.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping(path = "/")
class ExamplesController {
	
	/*@GetMapping("/")
	public String login(Model model) {
		return "login";
	}*/
	/*
	@GetMapping("/admin")
	public String index(Model model) {
		return "admin/index";
	}*/
	
	

	
	
	@GetMapping("/admin/simuladores/preguntas/add")
	public String simuladoresPreguntasAdd(Model model) {
		return "admin/components/simuladores/questions";
	}
	
	@GetMapping("/admin/simuladores/preguntas")
	public String simuladoresPreguntas(Model model) {
		return "admin/components/simuladores/list-preguntas";
	}
	
	@GetMapping("/admin/estudiantes")
	public String estudiantes(Model model) {
		return "admin/components/estudiantes/list";
	}
	
	@GetMapping("/admin/estudiantes/add")
	public String estudiantesAdd(Model model) {
		return "admin/components/estudiantes/crud";
	}
	
	@GetMapping("/admin/me")
	public String me(Model model) {
		return "admin/components/me/index";
	}
	
	@GetMapping("/admin/grupos")
	public String grupos(Model model) {
		return "admin/components/grupos/list";
	}
	
	@GetMapping("/admin/grupos/add")
	public String gruposAdd(Model model) {
		return "admin/components/grupos/crud";
	}
	
}
