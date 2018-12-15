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
import com.quizwish.quiz.entity.Grupo
import com.quizwish.quiz.models.User
import com.quizwish.quiz.services.GroupService

@Controller
@RequestMapping(path = "/admin")
class GroupController {
	
	private static final Log LOGGER = LogFactory.getLog(GroupController.class)
	
	private static final String SHOW = "admin/components/grupos/list"
	
	@Autowired
	@Qualifier("groupService")
	GroupService groupService
	
	@Autowired
	@Qualifier("sessionUser")
	SessionUser sessionUser
	
	@PreAuthorize("hasRole('ROLE_ROOT')")
	@GetMapping("/grupos")
	def show(Model model) {
		LOGGER.info("METHOD : show ");
		User user = sessionUser.userSessionAddUsername(model)
		List<Grupo> listGroup = groupService.getGroupByUser(user)
		model.addAttribute("listGroup", listGroup)
		return SHOW;
	}

}
