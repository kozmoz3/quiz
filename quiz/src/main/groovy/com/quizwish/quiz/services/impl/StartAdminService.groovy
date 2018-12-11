package com.quizwish.quiz.services.impl

import org.apache.commons.logging.Log

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

import com.quizwish.quiz.entity.Student
import com.quizwish.quiz.models.User
import com.quizwish.quiz.repositorys.StudentRepository
import com.quizwish.quiz.services.QuizService
import com.quizwish.quiz.services.StudentService

import org.apache.commons.logging.LogFactory

@Service("startAdminService")
class StartAdminService {
	private static final Log LOGGER = LogFactory.getLog(StartAdminService.class)
	
	@Autowired
	@Qualifier("studentService")
	StudentService studentService
	
	@Autowired
	@Qualifier("quizService")
	QuizService quizService
	
	public int countQuizByTeacher(User user) {
		LOGGER.info("METHOD: countQuizByTeacher")
		return quizService.getQuizByIduser(user).size();
	}
	
	public int countStudentByTeacher(int idTeacher) {
		LOGGER.info("METHOD: countStudentByTeacher")
		return studentService.findAllByIdTeacher(idTeacher).size();
	}
	
	

}
