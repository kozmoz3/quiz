package com.quizwish.quiz.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.quizwish.quiz.entity.Student;
import com.quizwish.quiz.models.User;
import com.quizwish.quiz.models.jmodelos.MUser;

public interface StudentService {
	
	public User updateUser(User user);

   public abstract User findUserById(Integer idUser);

	public abstract List<User> findAllStudent(Integer idTeacher);

	public abstract List<Student> findAllByIdTeacher(Integer idTeacher);

	public abstract Student save(User user, User userAdmin);
	
	public abstract User savePersonal(User user, MUser userdata);
	
	public abstract User saveProfile(User user, MultipartFile multipart);
}
