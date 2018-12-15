package com.quizwish.quiz.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

import com.quizwish.quiz.entity.Questions
import com.quizwish.quiz.services.QuestionsService
import com.quizwish.quiz.services.QuizService

@Controller
@RequestMapping(path = "/admin/simuladores")
class QuestionController {
	
	static final def LSTINDEX = "admin/components/simuladores/list-preguntas"
	static final def FRMPREGUNTAS = "admin/fragments/frmaddquestion"
	static final def PREGUNTAADD = "admin/components/simuladores/questions"
	
	@Autowired
	@Qualifier("questionsService")
	QuestionsService questionsService
	
	@Autowired
	@Qualifier("quizService")
	QuizService quizService
	
	@GetMapping("/preguntas")
	def simuladoresPreguntas(Model model) {
		return LSTINDEX
	}
	
	@GetMapping("/preguntas/frm/{id}")
	def simuladoresPreguntasFrm(@PathVariable(name = "id") int id, Model model) {
		model.addAttribute("edit", "yes")
		model.addAttribute("model", model)
		model.addAttribute("question", new Questions(1, "0,2","Un mensaje","Have the Scrum Team create Product Backlog items for each concern.##&&Add a Sprint to specifically resolve all security concerns.##&&Add security concerns to the definition of “Done”.##&&Postpone the work until a specialist can perform a…a list of security-related Product Backlog items.##&&Delegate the work to the concerned department." , "What are two good ways for a Scrum Team to ensure security concerns are satisfied? Choose 2 answers.", "checkbox", (short) 1 ) )
		return FRMPREGUNTAS
	}
	
	//def Questions(Integer idquestion, String answers, String options, String question, String type, short score) {
	
	@GetMapping("/preguntas/add")
	public String simuladoresPreguntasAdd(Model model) {
		return PREGUNTAADD
	}
}
