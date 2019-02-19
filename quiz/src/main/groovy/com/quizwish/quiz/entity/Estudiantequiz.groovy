package com.quizwish.quiz.entity

import javax.persistence.Basic
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

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
	
	
}
