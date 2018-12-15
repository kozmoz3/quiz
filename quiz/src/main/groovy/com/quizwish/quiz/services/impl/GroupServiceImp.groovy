package com.quizwish.quiz.services.impl

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.stereotype.Service

import com.quizwish.quiz.entity.Grupo
import com.quizwish.quiz.models.User
import com.quizwish.quiz.services.GroupService

@Service("groupService")
class GroupServiceImp implements GroupService{
	
	private static final Log LOGGER = LogFactory.getLog(GroupServiceImp.class)

	@Override
	public List<Grupo> getGroupByUser(User user) {
		LOGGER.info("METHOD : getGroupByUser ");
		return user.getGrupList();
	}

}
