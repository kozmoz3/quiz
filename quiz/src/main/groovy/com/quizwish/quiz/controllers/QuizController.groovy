package com.quizwish.quiz.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView

import com.quizwish.quiz.models.Quiz
import com.quizwish.quiz.services.QuizService
import com.quizwish.quiz.utils.SesionVariables
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import java.util.Map

@Controller
@RequestMapping(path = "/admin")
class QuizController {
	
	private static final Log LOGGER = LogFactory.getLog(QuizController.class)
     
	static final String INDEX = "admin/components/simuladores/list";
	 static final def SHOW = "admin/components/simuladores/crud";
	 static final def STORE = "admin/simuladores/preguntas/add";
	 
	 @Autowired
	 @Qualifier("quizService")
	 private QuizService quizService;
	 
	 @GetMapping("/simuladores")
	 public String index(Model model) {
		 return INDEX;
	 }
	 
	 @GetMapping("/simuladores/add")
	 def show(Model model) {
		 UserDetails details = SesionVariables.sesionDetails()
		 model.addAttribute("usuario", details.getUsername())
		 return SHOW;
	 }
	 
	 @PostMapping("/simuladores/add")
	 def addQuiz(@RequestParam Map<String,Object> quiz, Model model) {
		 System.out.println(quiz.toMapString())
		 return SHOW
	 }
	  
	@PostMapping("/saveQuiz")
	def save(@ModelAttribute("quiz")Quiz quiz) {
		def mov = new ModelAndView(STORE);
		mov.addObject("quiz",quiz)
		return mov;
	}
	
	@GetMapping("/simuladores/preguntas/add")
	public String simuladoresPreguntasAdd(Model model) {
		return "admin/components/simuladores/questions";
	}
}
