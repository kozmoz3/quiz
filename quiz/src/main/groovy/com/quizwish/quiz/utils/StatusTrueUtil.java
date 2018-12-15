package com.quizwish.quiz.utils;

import java.util.List;

import com.quizwish.quiz.entity.Grupo;
import com.quizwish.quiz.entity.Student;
import com.quizwish.quiz.models.User;

public class StatusTrueUtil {
	
	public static List<User> UserWithStatusTrue(List<User> listUser) {
		listUser.stream().filter(User -> User.isEnable() == true).forEach(System.out::println);
		return listUser;
	}
	
	public static List<Student> StudentWithStatusTrue(List<Student> listStudent) {
		listStudent.stream().filter(Student -> Student.isNullable() == true).forEach(System.out::println);
		return listStudent;
	}
	
	public static List<Grupo> groupWithStatusTrue(List<Grupo> listUser) {
		listUser.stream().filter(Grupo -> Grupo.isStatus() == true).forEach(System.out::println);
		return listUser;
	}

}
