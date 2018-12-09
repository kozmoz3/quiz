package com.quizwish.quiz.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

import com.quizwish.quiz.component.SessionUser
import com.quizwish.quiz.models.User

@Controller
@RequestMapping(path = "/admin")
class UserController {
     
	static final def SHOW = "admin/components/me/index";
	
	@Autowired
	@Qualifier("sessionUser")
	SessionUser sessionUser;
	
	@GetMapping("/user")
	def show(Model model) {
		User user = sessionUser.userSessionAll();
		model.addAttribute("user", user);
		return SHOW;
	}
}
