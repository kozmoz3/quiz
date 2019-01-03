package com.quizwish.quiz.models.jmodelos

import javax.persistence.Basic
import javax.persistence.Column
import javax.persistence.JoinColumn
import javax.persistence.Lob
import javax.persistence.ManyToOne

import com.quizwish.quiz.entity.Quiz

class MQuestion {
	Integer idquestion
	List<String> answers
	String message
	List<String> options
	String question
	String type
	short score
	int idquiz
	@Override
	public String toString() {
		return "MQuestion [idquestion=" + idquestion + ", answers=" + answers + ", message=" + message + ", options=" + options + ", question=" + question + ", type=" + type + ", score=" + score + ", idquiz=" + idquiz + "]";
	}
	
	
}
