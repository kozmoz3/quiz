package com.quizwish.quiz.component;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.quizwish.quiz.entity.Questions;
import com.quizwish.quiz.entity.Respuestas;
import com.quizwish.quiz.models.jmodelos.MRespuestas;
import com.quizwish.quiz.services.QuestionsService;

@Component("respuestasComponent")
public class RespuestasComponent {

	@Autowired
	@Qualifier("questionsService")
	QuestionsService questionsService;

	public List<Integer> getPreguntas(Respuestas respuestas) {
		String[] preguntar = respuestas.getPrespuestas().split("\\|");
		List<Integer> listaid = new ArrayList<>();
		for (String pregunta : preguntar) {
			String[] preguntaid = pregunta.split("&");
			listaid.add(Integer.parseInt(preguntaid[0]));
		}
		return listaid;
	}

	public String getRespuestas(String resp) {
		String[] preguntaid = resp.split("&");
		return preguntaid[1];
	}

	public String getRespuesta(Respuestas respuestas, Integer id) {
		String[] preguntar = respuestas.getPrespuestas().split("\\|");
		for (String pregunta : preguntar) {
			String[] preguntaid = pregunta.split("&");
			if (id.equals(Integer.parseInt(preguntaid[0])))
				return pregunta;
		}
		return "";
	}

	@SuppressWarnings("unchecked")
	public List<MRespuestas> getMisRespuestas(Respuestas respuestas) {
		List<Integer> ids = getPreguntas(respuestas);
		List<Questions> questions = (List<Questions>) questionsService.getAllQuestionsById(ids);

		List<MRespuestas> misrespuestas = new ArrayList<>();
		questions.forEach(it -> {
			String resp = getRespuesta(respuestas, it.getIdquestion());
			if (!resp.equals(""))
				misrespuestas.add(new MRespuestas(it.getIdquestion(), it.getAnswers(), it.getMessage(), it.getOptions(),
						it.getQuestion(), it.getType(), getRespuestas(resp), respuestas.getCalificacion(),
						respuestas.getCorrectas(), respuestas.getIncorrectas()));
		});
		return misrespuestas;
	}
}
