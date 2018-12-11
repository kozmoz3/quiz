package com.quizwish.quiz.services.impl

import org.apache.commons.logging.Log

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

import com.quizwish.quiz.entity.Student
import com.quizwish.quiz.models.User
import com.quizwish.quiz.repositorys.StudentRepository
import com.quizwish.quiz.services.QuizService
import org.apache.commons.logging.LogFactory

@Service("startAdminService")
class StartAdminService {
	private static final Log LOGGER = LogFactory.getLog(StartAdminService.class)
	
	@Autowired
	@Qualifier("studentRepository")
	StudentRepository studentRepository
	
	@Autowired
	@Qualifier("quizService")
	QuizService quizService
	
	public int countQuizByTeacher(User user) {
		return quizService.getQuizByIduser(user).size();
	}
	
	public int countStudentByTeacher(int idTeacher) {
		LOGGER.info("METHOD: countStudentByTeacher")
		return findAllByIdTeacher(idTeacher).size();
	}
	
	public List<Student>  findAllByIdTeacher(Integer idTeacher) {
		LOGGER.info("METHOD: findAllByIdTeacher")
		//studenRepository.
		
		LOGGER.info("METHOD: findAllByIdTeacher -- "+studentRepository.findByTeacher( idTeacher))
		return studentRepository.findByTeacher( idTeacher);
	}

}
