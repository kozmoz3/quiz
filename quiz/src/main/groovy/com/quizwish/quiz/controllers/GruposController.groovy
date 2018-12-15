package com.quizwish.quiz.controllers

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

import com.quizwish.quiz.component.SessionUser
import com.quizwish.quiz.entity.Grupo
import com.quizwish.quiz.entity.Grupousuario
import com.quizwish.quiz.models.User
import com.quizwish.quiz.services.GroupService
import com.quizwish.quiz.services.UsuarioService
import com.quizwish.quiz.utils.StatusTrueUtil

@Controller
@RequestMapping(path = "/admin")
class GruposController {
	
	private static final Log LOGGER = LogFactory.getLog(GruposController.class)
	static final def SHOW = "admin/components/grupos/list"
	static final def INDEX = "admin/components/grupos/crud"
	
	@Autowired
	@Qualifier("sessionUser")
	SessionUser sessionUser
	
	@Autowired
	@Qualifier("usuarioService")
	UsuarioService usuarioService
	
	@Autowired
	@Qualifier("grupoService")
	GroupService grupoService
	
	@PreAuthorize("hasRole('ROLE_ROOT')")
	@GetMapping("/grupos")
	def show(Model model) {
		LOGGER.info("METHOD : show ")
		User user = sessionUser.userSessionAddUsername(model)
		List<Grupo> lstgrupos =  grupoService.getGroupAllByUser(user);
		model.addAttribute("lstgrupos",lstgrupos)
		return SHOW
	}
	
	@PreAuthorize("hasRole('ROLE_ROOT')")
	@GetMapping("/grupos/add")
	def index(Model model) {
		LOGGER.info("METHOD : index ")
		User user = sessionUser.userSessionAddUsername(model)
		List<User> listUser =  grupoService.getStudentAllByUserId(user)
		model.addAttribute("listUser",  listUser)
		model.addAttribute("grupo", new Grupousuario() )
		return INDEX
	}
	
	@PreAuthorize("hasRole('ROLE_ROOT')")
	@GetMapping("/grupos/edit/{id}")
	def gruposEdit(@PathVariable(name = "id") int id, Model model) {
		List<User> lstusr = usuarioService.getUsuarioAll()
		User users = sessionUser.userSessionAddUsername(model)
		model.addAttribute("listUser",  lstusr.findAll { it.getIdrol() == 2 } )
		model.addAttribute("grupo", users.getGrupousuarioList().findAll{ it.getIdgrupo().getIdgrupo().equals(id) } )
		return "admin/components/grupos/crud"
	}
	
	@PreAuthorize("hasRole('ROLE_ROOT')")
	@PostMapping("/grupos/edit/{id}")
	def gruposEditPost(Model model) {
		return "admin/components/grupos/crud"
	}

}
