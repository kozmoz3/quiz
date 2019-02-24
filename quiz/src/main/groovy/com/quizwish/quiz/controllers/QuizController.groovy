package com.quizwish.quiz.controllers

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.access.prepost.PreAuthorize
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

//import com.quizwish.quiz.component.QuizComponent
import com.quizwish.quiz.component.SessionUser
import com.quizwish.quiz.entity.Grupo
import com.quizwish.quiz.entity.Quiz
import com.quizwish.quiz.models.User
import com.quizwish.quiz.services.GroupService
import com.quizwish.quiz.services.QuizService
import com.quizwish.quiz.utils.SesionVariables
import java.util.List;

@Controller
@RequestMapping(path = "/admin")
class QuizController {

	private static final Log LOGGER = LogFactory.getLog(QuizController.class)

	static final String INDEX = "admin/components/simuladores/list";
	static final def BLANK = "blank"
	static final def SHOW = "admin/components/simuladores/crud";
	static final def STORE = "admin/simuladores/preguntas/add";
	static final def PREGUNTAADD = "admin/components/simuladores/questions"
	static final String RELACIONGRUPO = "admin/components/simuladores/relaciong";

	@Autowired
	@Qualifier("sessionUser")
	SessionUser sessionUser;

	@Autowired
	@Qualifier("quizService")
	QuizService quizService;
	
	@Autowired
	@Qualifier("grupoService")
	GroupService grupoService
	

	@PreAuthorize("hasRole('ROLE_ROOT')")
	@GetMapping("/simuladores")
	public String index(Model model) {
		LOGGER.info("METHOD : index --");
		User user = sessionUser.userSessionAddUsername(model);
		List<Quiz> quizList=  quizService.getQuizByIduser(user);
		LOGGER.info("METHOD : index -- listQuiz = "+quizList );
		model.addAttribute("quizList", quizList);
		return INDEX;
	}

	@PreAuthorize("hasRole('ROLE_ROOT')")
	@GetMapping("/simuladores/add")
	def show(Model model) {
		LOGGER.info("METHOD : show --");
		User user = sessionUser.userSessionAddUsername(model);
		model.addAttribute("quiz", new Quiz());
		return SHOW;
	}

	@PostMapping("/simuladores/add")
	def stores(@RequestParam Map<String,Object> quizMap, Model model) {
		LOGGER.info("METHOD : stores -- "+ quizMap);
		User user = sessionUser.userSessionAddUsername(model);
		Quiz quiz = quizService.saveQuiz(quizMap, user)
		return  PREGUNTAADD
	}
	
	@PreAuthorize("hasRole('ROLE_ROOT')")
	@GetMapping("/relacion/grupos/{id}")
	def addGrupoList(@PathVariable("id")Integer id, Model model) {
		LOGGER.info("METHOD : addGrupoList --");
		User user = sessionUser.userSessionAddUsername(model);
		List<Grupo> lstgrupos =  grupoService.getGroupAllByUser(user);
		Quiz quiz =  quizService.getQuizById(id)
		LOGGER.info("METHOD : addGrupoList -- quiz = " + quiz );
		model.addAttribute("quiz", quiz);
		model.addAttribute("lstgrupos",lstgrupos)
		return RELACIONGRUPO;
	}
	
	@PreAuthorize("hasRole('ROLE_ROOT')")
	@PostMapping("/relacion/grupos/{idquiz}/{idgrupo}/{estatus}")
	def addGrupo(
		@PathVariable("idquiz") Integer idquiz, 
		@PathVariable("idgrupo") Integer idgrupo, 
		@PathVariable("estatus") boolean estatus, Model model) {		
		quizService.relacionQuizGrupo(idquiz, idgrupo, estatus)
		return BLANK;
	}

	
}
