package com.quizwish.quiz.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

import com.quizwish.quiz.services.QuestionsService
import com.quizwish.quiz.services.QuizService

@Controller
@RequestMapping(path = "/admin/simuladores")
class ResultadosAdminController {
	
	static final def LSTINDEX = "admin/components/simuladores/result"
	
	@Autowired
	@Qualifier("questionsService")
	QuestionsService questionsService
	
	@Autowired
	@Qualifier("quizService")
	QuizService quizService
	
	@GetMapping("/resultados/{id}")
	def simuladoresPreguntas(@PathVariable(name = "id") int id,Model model) {
		model.addAttribute("quiz", quizService.getQuizById(id) )
		return LSTINDEX
	}

}
