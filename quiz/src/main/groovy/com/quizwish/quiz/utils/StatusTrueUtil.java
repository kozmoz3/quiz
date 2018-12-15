package com.quizwish.quiz.utils;

import java.util.List;

import com.quizwish.quiz.entity.Grupo;
import com.quizwish.quiz.models.User;

public class StatusTrueUtil {
	
	public static List<User> StudentWithStatusTrue(List<User> listUser) {
		listUser.stream().filter(User -> User.isEnable() == true).forEach(System.out::println);
		return listUser;
	}
	
	public static List<Grupo> grouptWithStatusTrue(List<Grupo> listUser) {
		listUser.stream().filter(Grupo -> Grupo.isStatus() == true).forEach(System.out::println);
		return listUser;
	}

}
