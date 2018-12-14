package com.quizwish.quiz.services;

import java.util.List;

import com.quizwish.quiz.entity.Student;
import com.quizwish.quiz.models.User;

public interface StudentService {
	
	public abstract List<User>  findAllStudent(Integer idTeacher) ;

	public abstract List<Student>  findAllByIdTeacher(Integer idTeacher);
	
	public abstract Student  save(User user, User userAdmin );
}
