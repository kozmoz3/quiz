package com.quizwish.quiz.controllers

import com.quizwish.quiz.models.Quiz
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping(path = "/")
class ExamplesController {
	
<<<<<<< HEAD
	@GetMapping("/")
	def login(Model model) {
=======
	/*@GetMapping("/")
	public String login(Model model) {
>>>>>>> 54915b48a254bf3302477c6289004a88ddb0beab
		return "login";
	}*/
	/*
	@GetMapping("/admin")
	def index(Model model) {
		return "admin/index";
	}*/
	
<<<<<<< HEAD
	@GetMapping("/admin/simuladores")
	def simuladores(Model model) {
=======
	/*@GetMapping("/admin/simuladores")
	public String simuladores(Model model) {
>>>>>>> 54915b48a254bf3302477c6289004a88ddb0beab
		return "admin/components/simuladores/list";
	}*/

<<<<<<< HEAD
	@GetMapping("/admin/simuladores/add")
	def simuladoresAdd(Model model) {
		return "admin/components/simuladores/crud";
	}
=======
	
>>>>>>> 54915b48a254bf3302477c6289004a88ddb0beab
	
	@GetMapping("/admin/simuladores/preguntas/add")
	def simuladoresPreguntasAdd(Model model) {
		return "admin/components/simuladores/questions";
	}
	
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
	
	@PostMapping("/admin/actions/simuladores/add")
	def addQuiz(@ModelAttribute Object quiz) {
		System.out.println(quiz.toString());
		return quiz;
	}
	
}
