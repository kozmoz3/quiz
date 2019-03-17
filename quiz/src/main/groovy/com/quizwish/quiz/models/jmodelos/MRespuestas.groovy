package com.quizwish.quiz.models.jmodelos

class MRespuestas {
	Integer idquestion
	String answers
	String message
	String options
	String question
	String type
	String prespuestas
	double calificacion
	double correctas
	double incorrectas
	public MRespuestas(Integer idquestion, String answers, String message, String options, String question, String type,
			String prespuestas, double calificacion, double correctas, double incorrectas) {
		super();
		this.idquestion = idquestion;
		this.answers = answers;
		this.message = message;
		this.options = options;
		this.question = question;
		this.type = type;
		this.prespuestas = prespuestas;
		this.calificacion = calificacion;
		this.correctas = correctas;
		this.incorrectas = incorrectas;
	}
	
	
}
