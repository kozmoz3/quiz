package com.quizwish.quiz.services.impl

import java.lang.reflect.Array

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

import com.quizwish.quiz.entity.Student
import com.quizwish.quiz.models.User
import com.quizwish.quiz.repositorys.StudentRepository
import com.quizwish.quiz.repositorys.UserRepository
import com.quizwish.quiz.services.StudentService

@Service("studentService")
class StudentServiceImpl implements StudentService {
	
	private static final Log LOGGER = LogFactory.getLog(StudentServiceImpl.class)
	
	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository
	
	@Autowired
	@Qualifier("studentRepository")
	private StudentRepository studentRepository

	@Override
	public List<User>  findAllStudent(Integer idTeacher) {
		LOGGER.info("METHOD: findAllStudent")
		List<Student> studentList = findAllByIdTeacher(idTeacher);
		List<User> userList = new ArrayList();
		for(Student students : studentList) {
			userList.add(userRepository.findAllById(students.getId()));
		}
		LOGGER.info("METHOD: findAllStudent --- listStudent "+userList)
		return userList;
	}
	
	@Override
	public List<Student>  findAllByIdTeacher(Integer idTeacher) {
		LOGGER.info("METHOD: findAllByIdTeacher")
		return studentRepository.findByTeacher( idTeacher);
	}

}
