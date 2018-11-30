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
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

@Controller
@RequestMapping(path = "/admin")
class QuizController {
	
	private static final Log LOGGER = LogFactory.getLog(QuizController.class)
     
	static final String INDEX = "admin/components/simuladores/list"
	 static final def SHOW = "admin/components/simuladores/crud";
	 static final def STORE = "admin/simuladores/preguntas/add";
	 
	 @Autowired
	 @Qualifier("quizService")
	 private QuizService quizService;
	 
	 @GetMapping("/simuladores")
	 def simuladores(Model model) {
		 LOGGER.info("entro")
		 return "admin/components/simuladores/list";
	 }
	 
	 @GetMapping("/simuladores/add")
	 def show(Model model) {
		 return SHOW;
	 }
	  
	@PostMapping("/saveQuiz")
	def save(@ModelAttribute("quiz")Quiz quiz) {
		def mov = new ModelAndView(STORE);
		mov.addObject("quiz",quiz)
		return mov;
	}
	
	@GetMapping("/admin/simuladores/preguntas/add")
	public String simuladoresPreguntasAdd(Model model) {
		return "admin/components/simuladores/questions";
	}
}
