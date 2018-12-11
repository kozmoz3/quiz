package com.quizwish.quiz.services.impl

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

import com.quizwish.quiz.entity.Student
import com.quizwish.quiz.repositorys.StudentRepository

@Service("studentService")
class StudentService {
	
	private static final Log LOGGER = LogFactory.getLog(StudentService.class)
	
	@Autowired
	@Qualifier("studentRepository")
	StudentRepository studentRepository

	public List<Student>  findAllByIdTeacher(Integer idTeacher) {
		LOGGER.info("METHOD: findAllByIdTeacher")
		return studentRepository.findByTeacher( idTeacher);
	}
}
