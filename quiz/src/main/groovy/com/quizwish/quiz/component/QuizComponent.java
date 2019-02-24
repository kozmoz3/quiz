package com.quizwish.quiz.component;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.quizwish.quiz.services.QuizService;
import com.quizwish.quiz.services.QuizgrupoService;
import com.quizwish.quiz.entity.Grupo;
import com.quizwish.quiz.entity.Quiz;
import com.quizwish.quiz.entity.Quizgrupo;
import com.quizwish.quiz.models.User;

@Component("quizComponent")
public class QuizComponent {

	@Autowired
	@Qualifier("quizService")
	QuizService quizService;
	
	@Autowired
	@Qualifier("quizgrupoService")
	QuizgrupoService quizgrupoService;

	public List<Quiz> quizWithStatusTrue(User user) {
		List<Quiz> quizList = quizService.getQuizByIduser(user);
		quizList.stream().filter(Quiz -> Quiz.isEstatus() == true).forEach(System.out::println);
		return quizList;
	}

	public List<User> StudentWithStatusTrue(List<User> listUser) {
		listUser.stream().filter(User -> User.isEnable() == true).forEach(System.out::println);
		return listUser;
	}
	
	public Quizgrupo getOnlyOneQuizgrupo(Grupo grupo, Quiz idquiz) {
		List<Quizgrupo> grupousuario = quizgrupoService.findAllByIdquiz(idquiz)
			.stream()
			.filter(grupu -> grupu.getIdgrupo().getIdgrupo().equals(grupo.getIdgrupo()))
			.collect(Collectors.toList());
		return grupousuario.isEmpty() ? new Quizgrupo() : grupousuario.get(0);
	}
	
	
}
