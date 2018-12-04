package com.quizwish.quiz.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

import com.quizwish.quiz.entity.Quiz

@Controller
@RequestMapping(path = "/")
class ExamplesController {
		
	@GetMapping("/admin/simuladores/preguntas")
	def simuladoresPreguntas(Model model) {
		return "admin/components/simuladores/list-preguntas";
	}
	
	@GetMapping("/admin/estudiantes")
	def estudiantes(Model model) {
		return "admin/components/estudiantes/list";
	}
	
	@GetMapping("/admin/estudiantes/add")
	def estudiantesAdd(Model model) {
		return "admin/components/estudiantes/crud";
	}
	
	@GetMapping("/admin/me")
	def me(Model model) {
		return "admin/components/me/index";
	}
	
	@GetMapping("/admin/grupos")
	def grupos(Model model) {
		return "admin/components/grupos/list";
	}
	
	@GetMapping("/admin/grupos/add")
	def gruposAdd(Model model) {
		return "admin/components/grupos/crud";
	}
	
}
