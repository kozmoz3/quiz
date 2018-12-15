package com.quizwish.quiz.services

import com.quizwish.quiz.entity.Grupo
import com.quizwish.quiz.models.User

public interface GroupService {

	public List<Grupo> getGroupByUser(User user);
	
}
