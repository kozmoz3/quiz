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
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView

//import com.quizwish.quiz.component.QuizComponent
import com.quizwish.quiz.component.SessionUser
import com.quizwish.quiz.entity.Quiz
import com.quizwish.quiz.models.User
import com.quizwish.quiz.services.QuizService
import com.quizwish.quiz.utils.SesionVariables
import java.util.List;

@Controller
@RequestMapping(path = "/admin")
class QuizController {

	private static final Log LOGGER = LogFactory.getLog(QuizController.class)

	static final String INDEX = "admin/components/simuladores/list";
	static final def SHOW = "admin/components/simuladores/crud";
	static final def STORE = "admin/simuladores/preguntas/add";

	@Autowired
	@Qualifier("sessionUser")
	SessionUser sessionUser;

	@Autowired
	@Qualifier("quizService")
	QuizService quizService;
	
	/*@Autowired
	@Qualifier("quizComponent")
	QuizComponent quizComponent;*/

	@PreAuthorize("hasRole('ROLE_ROOT')")
	@GetMapping("/simuladores")
	public String index(Model model) {
		LOGGER.info("METHOD : index --");
		User user = sessionUser.userSessionAll();
		def username = user.getNombre();
		model.addAttribute("username", username);
		//List<Quiz> quizList= quizComponent.quizWithStatusTrue(user);
		//model.addAttribute("quizList", quizList);
		//LOGGER.info("METHOD : index -- listQuiz = "+quizList );
		return INDEX;
	}

	@PreAuthorize("hasRole('ROLE_ROOT')")
	@GetMapping("/simuladores/add")
	def show(Model model) {
		LOGGER.info("METHOD : show --");
		User user = sessionUser.userSessionAll();
		def username = user.getNombre();
		model.addAttribute("username", username);
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
