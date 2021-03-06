package com.quizwish.quiz.entity

import javax.persistence.Basic
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

import com.fasterxml.jackson.annotation.JsonBackReference

@Entity
@Table(name = "studentquiz")
class Estudiantequiz {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idstudentquiz")
	int idStudentQuiz;
	
	@Column(name = "quiz")
	int quiz
	
	@Column(name = "student")
	int student
	
	@Column(name = "teacher")
	int teacher
	
	@Column(name = "status")
	boolean status
	
	@JoinColumn(name = "idquiz", referencedColumnName = "idquiz")
	@ManyToOne(optional = false)
	@JsonBackReference
	Quiz idquiz
	
	
}
