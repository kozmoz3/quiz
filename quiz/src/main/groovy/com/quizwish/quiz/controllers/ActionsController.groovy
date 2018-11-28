package com.quizwish.quiz.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

import com.quizwish.quiz.models.Quiz
import com.quizwish.quiz.services.QuizServiceImp

@Controller
class ActionsController {
	@Autowired
	@Qualifier("serviceQuiz")
	QuizServiceImp serviceQuiz
	
	@PostMapping("/admin/actions/simuladores/add")
	def simuladoresAdd(@RequestParam(defaultValue = "0") Map<String, Object> simuladorInfo, Model model) {
		System.out.println(simuladorInfo);
		//simuladorInfo.each{it->"Clave: ${it.key} Valor: ${it.value}"}
		//serviceQuiz.setQuiz(new Quiz());
	}
}
