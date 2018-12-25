package com.quizwish.quiz.controllers

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

import com.quizwish.quiz.entity.Questions
import com.quizwish.quiz.models.jmodelos.MQuestion
import com.quizwish.quiz.services.QuestionsService
import com.quizwish.quiz.services.QuizService

@Controller
@RequestMapping(path = "/admin/simuladores")
class QuestionController {
	
	private static final Log LOGGER = LogFactory.getLog(QuestionController.class)
	static final def LSTINDEX = "admin/components/simuladores/list-preguntas"
	static final def FRMPREGUNTAS = "admin/fragments/frmaddquestion"
	static final def PREGUNTAADD = "admin/components/simuladores/questions"
	static final def REDIRECT = "redirect:/admin/simuladores"
	
	@Autowired
	@Qualifier("questionsService")
	QuestionsService questionsService
	
	@Autowired
	@Qualifier("quizService")
	QuizService quizService
	
	@GetMapping("/preguntas/{id}")
	def simuladoresPreguntas(@PathVariable(name = "id") int id,Model model) {
		model.addAttribute("quiz", quizService.getQuizById(id) )		
		return LSTINDEX
	}
	
	@GetMapping("/preguntas/frm/{id}/{iddom}")
	def simuladoresPreguntasFrm(@PathVariable(name = "id") int id, @PathVariable(name = "iddom") int iddom, Model model) {
		model.addAttribute("edit", "yes")
		model.addAttribute("model", model)
		model.addAttribute("iddom", iddom)
		model.addAttribute("question", questionsService.getQuestionsById(id) )
		return FRMPREGUNTAS
	}
	
	@GetMapping("/preguntas/add/{id}")
	public String simuladoresPreguntasAdd(@PathVariable(name = "id") int id,Model model) {
		model.addAttribute("quiz", quizService.getQuizById(id) )
		return PREGUNTAADD
	}
	
	@RequestMapping(value = "/preguntas/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	def setAddPreguntas(@RequestBody List<MQuestion> mquestion, Model model) {
		mquestion.each { it -> LOGGER.info( it.toString() ) }
		questionsService.setQuestions(mquestion)
		return REDIRECT
	}
	
	@RequestMapping(value = "/preguntas/edit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	def setEditPreguntas(@RequestBody MQuestion mquestion, Model model) {
		questionsService.setQuestion(mquestion)
		return REDIRECT
	}
}
