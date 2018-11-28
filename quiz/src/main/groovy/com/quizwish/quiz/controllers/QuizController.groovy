package com.quizwish.quiz.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

import com.quizwish.quiz.models.Quiz
import com.quizwish.quiz.services.QuizService

@Controller
@RequestMapping(path = "/")
class QuizController {
     
	 static final def INDEX = "admin/components/simuladores/crud";
	 static final def QUIZ = "quiz";
	 
	 @Autowired
	 @Qualifier("quizService")
	 private QuizService quizService;
	 
	 @GetMapping("/admin/simuladores/add")
	 def index(Model model) {
		 return INDEX;
	 }
	  
	@PostMapping("/saveQuiz")
	def saveQuiz(@ModelAttribute("quiz")Quiz quiz) {
		def mov = new ModelAndView(QUIZ);
		mov.addObject("quiz",quiz)
		return mov;
	}
}
