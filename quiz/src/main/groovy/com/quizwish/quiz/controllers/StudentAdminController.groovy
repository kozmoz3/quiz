package com.quizwish.quiz.controllers

import javax.validation.Valid

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

import com.quizwish.quiz.component.SessionUser
import com.quizwish.quiz.models.User
import com.quizwish.quiz.services.StudentService

@RequestMapping(path = "/admin")
@Controller
class StudentAdminController {
	
	private static final Log LOGGER = LogFactory.getLog(StudentAdminController.class)
	
	private static final String UPDATE = "admin/components/estudiantes/update";
	
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
		List<User> liststudent = studentService.findAllStudent(userAdmin.getIduser());
		model.addAttribute("liststudent", liststudent);
		return "admin/components/estudiantes/list";
	}
	
	@PreAuthorize("hasRole('ROLE_ROOT')")
	@GetMapping("/estudiantes/add")
	def create(Model model) {
		LOGGER.info("METHOD : create");
		sessionUser.userSessionAddUsername(model);
		model.addAttribute("users", new User());
		return "admin/components/estudiantes/crud";
	}
	
	@PreAuthorize("hasRole('ROLE_ROOT')")
	@PostMapping("/estudiantes/addstudent")
	def store(@Valid User users, BindingResult bindingResult) {
		LOGGER.info("METHOD : store -- map : "+users.toString());
		User userAdmin = sessionUser.userSessionAll();
		if(bindingResult.hasErrors()){
			LOGGER.info("METHOD : store -- error : "+bindingResult.allErrors);
			return "redirect:/admin/estudiantes/add";
		}else {
			def est = studentService.save(users, userAdmin)
			LOGGER.info("METHOD : store -- map : "+est);
			return "redirect:/admin/estudiantes";
		}	
	}
	
	@PreAuthorize("hasRole('ROLE_ROOT')")
	@GetMapping("/estudiantes/edit/{id}")
	def edit(@PathVariable("id")Integer id, Model model) {
		LOGGER.info("METHOD : edit -- id usuario : "+id);
		User users = studentService.findUserById(id)
		LOGGER.info("METHOD : edit -- usuario : "+users.toString());
		model.addAttribute("users", users);
		return UPDATE;
	}
	
	@PreAuthorize("hasRole('ROLE_ROOT')")
	@PostMapping("/estudiantes/update")
	def update(@Valid User users, BindingResult bindingResult, Model model) {
		LOGGER.info("METHOD : update : user "+ users.toString());
		if(bindingResult.hasErrors()){
			LOGGER.info("METHOD : store -- error : "+bindingResult.allErrors);
			return "redirect:/admin/estudiantes/add";
		}else {
			studentService.updateUser(users)
			
			return "redirect:/admin/estudiantes";
		}
		return UPDATE;
	}
	
}
