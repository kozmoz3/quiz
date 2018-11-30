package com.quizwish.quiz.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile
import com.quizwish.quiz.models.Quiz
import com.quizwish.quiz.services.QuizServiceImp

@Controller
class ActionsController {
	
	private static String filePath = System.getProperty("catalina.home") + File.separator + "webapps"+File.separator+"images"+File.separator;
	
	@Autowired
	@Qualifier("serviceQuiz")
	QuizServiceImp serviceQuiz

	/*@PostMapping("/admin/actions/simuladores/add")
	def uploadFile(
		@RequestParam("img")MultipartFile img, 
		@RequestParam("nombre")String nombre, 
		@RequestParam("descripcion")String descripcion) {
		System.out.println(nombre + " => " + descripcion);		
		if(!img.isEmpty()) {
			try {
				img.transferTo(new File(filePath+img.getOriginalFilename()));
			} catch(Exception e) {
								
			}
		}
	}*/
}
