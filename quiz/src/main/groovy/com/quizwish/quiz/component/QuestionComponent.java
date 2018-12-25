package com.quizwish.quiz.component;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.quizwish.quiz.entity.Questions;
import com.quizwish.quiz.entity.Quiz;
import com.quizwish.quiz.models.jmodelos.MQuestion;
import com.quizwish.quiz.services.QuestionsService;
import com.quizwish.quiz.services.QuizService;

@Component("questioncomponent")
public class QuestionComponent {
	
	@Autowired
	@Qualifier("questionsService")
	QuestionsService questionsService;
	
	@Autowired
	@Qualifier("quizService")
	QuizService quizService;
	
	public List<Questions> setQuestions(List<MQuestion> lstQuest) {
		if(lstQuest.isEmpty()) return new ArrayList<>();
		
		List<Questions> lstQuestions = new ArrayList<>();
		Quiz idquiz = (Quiz) quizService.getQuizById( lstQuest.get(0).getIdquiz() );
		
		lstQuest.forEach( question -> {
			String options = getOptions( question.getOptions() );
			String answers = getAnswers( question.getAnswers() ); 
			lstQuestions.add(new Questions(
					answers.substring(0, answers.length() - 1),
					question.getMessage(),
					options.substring(0, options.length() - 4),
					question.getQuestion(),
					question.getType(),
					question.getScore(), idquiz));
		});		
		return lstQuestions.isEmpty() ? new ArrayList<>() : lstQuestions;
	}
	
	public Questions setQuestion(MQuestion qmuestion) {
		Questions quiz = (Questions) questionsService.getQuestionsById(qmuestion.getIdquestion());
		quiz.setMessage(qmuestion.getMessage());
		quiz.setQuestion(qmuestion.getQuestion());
		quiz.setScore(qmuestion.getScore());
		quiz.setType(qmuestion.getType());
		
		String options = getOptions( qmuestion.getOptions() );
		String answers = getAnswers( qmuestion.getAnswers() );
		
		quiz.setOptions( options.substring(0, options.length() - 4) );
		quiz.setAnswers( answers.substring(0, answers.length() - 1) );		
		return quiz != null ? quiz : new Questions();
	}
	
	private String getOptions(List<String> lstoptions) {
		StringBuilder stroptions = new StringBuilder();
		lstoptions.forEach( options -> stroptions.append(options).append("##&&"));
		return stroptions.toString();
	}
	
	private String getAnswers(List<String> lstanswers) {
		StringBuilder stranswers = new StringBuilder();
		lstanswers.forEach( answers -> stranswers.append(answers).append(","));
		return stranswers.toString();
	}
}
