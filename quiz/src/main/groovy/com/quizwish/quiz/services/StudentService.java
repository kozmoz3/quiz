package com.quizwish.quiz.services;

import java.util.List;

import com.quizwish.quiz.entity.Student;

public interface StudentService {

	public abstract List<Student>  findAllByIdTeacher(Integer idTeacher);
}
