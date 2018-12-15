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
import com.quizwish.quiz.services.UsuarioService

@Service("studentService")
class StudentServiceImpl implements StudentService {
	
	private static final Log LOGGER = LogFactory.getLog(StudentServiceImpl.class)
	
	@Autowired
	@Qualifier("usuarioService")
	private UsuarioService usuarioService
	
	@Autowired
	@Qualifier("studentRepository")
	private StudentRepository studentRepository
    
	@Override
	public User findUserById(Integer idUser) {
		return usuarioService.findById(idUser);
	}
	
	@Override
	public User updateUser(User user) {
		return usuarioService.updateUser(user)
	}
	
	@Override
	public List<User>  findAllStudent(Integer idTeacher) {
		LOGGER.info("METHOD: findAllStudent")
		List<Student> studentList = findAllByIdTeacher(idTeacher);
		List<User> userList = new ArrayList();
		for(Student students : studentList) {
			userList.add(usuarioService.findById(students.getStudent()));
		}	
		return userList;
	}
	
	@Override
	public List<Student>  findAllByIdTeacher(Integer idTeacher) {
		LOGGER.info("METHOD: findAllByIdTeacher")
		return studentRepository.findByTeacher( idTeacher);
	}

	@Override
	public Student save(User user, User userAdmin) {
		def userExist = usuarioService.getByCorreo(user.getCorreo()) 
		LOGGER.info("METHOD: save "+userExist.toString())
		if(userExist == null) {
		return createStudent(user, userAdmin)
		}
		return createUserExist(userExist, userAdmin);
	}
	
	private Student createUserExist(User user, User userAdmin) {
		LOGGER.info("METHOD: createUserExist ")
		return saveStudent(user.getIduser(),userAdmin.getIduser())	
	}
	
	private Student createStudent(User user, User userAdmin) {
		LOGGER.info("METHOD: createStudent")
		int rol = 2;
		User userCreated = usuarioService.save(user, rol)
		return saveStudent(userCreated.getIduser(), userAdmin.getIduser())
	}
	
	private Student saveStudent(Integer idStudent, Integer idTeacher) {
		LOGGER.info("METHOD: saveStudent")
		Student student = new Student();
		student.setStudent(idStudent)
		student.setTeacher( idTeacher)
		student.setNullable(true);
		
		return studentRepository.save(student);
	}

}
