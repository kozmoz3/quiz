package com.quizwish.quiz.controllers

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

import com.quizwish.quiz.component.SessionUser
import com.quizwish.quiz.models.User
import com.quizwish.quiz.services.StudentService

@RequestMapping(path = "/admin")
@Controller
class StudentAdminController {
	
	private static final Log LOGGER = LogFactory.getLog(StudentAdminController.class)
	
	@Autowired
	@Qualifier("studentService")
	StudentService studentService
	
	@Autowired
	@Qualifier("sessionUser")
	SessionUser sessionUser;

	@PreAuthorize("hasRole('ROLE_ROOT')")
	@GetMapping("/estudiantes")
	def show(Model model) {
		LOGGER.info("METHOD : show");
		User userAdmin = sessionUser.userSessionAddUsername(model);
		List<User> listuser =studentService.findAllStudent(userAdmin.getIduser());
		model.addAttribute("listuser", listuser);
		return "admin/components/estudiantes/list";
	}
}
