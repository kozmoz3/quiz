package com.quizwish.quiz.entity

import java.io.Serializable
import javax.persistence.Basic
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.Lob
import javax.persistence.ManyToOne
import javax.persistence.NamedQueries
import javax.persistence.NamedQuery
import javax.persistence.Table
import javax.xml.bind.annotation.XmlRootElement

@Entity
@Table(name = "questions", catalog = "quiz", schema = "")
@XmlRootElement
/*@NamedQueries({
	@NamedQuery(name = "Questions.findAll", query = "SELECT q FROM Questions q")
	, @NamedQuery(name = "Questions.findByIdquestion", query = "SELECT q FROM Questions q WHERE q.idquestion = :idquestion")
	, @NamedQuery(name = "Questions.findByMessage", query = "SELECT q FROM Questions q WHERE q.message = :message")
	, @NamedQuery(name = "Questions.findByQuestion", query = "SELECT q FROM Questions q WHERE q.question = :question")
	, @NamedQuery(name = "Questions.findByType", query = "SELECT q FROM Questions q WHERE q.type = :type")
	, @NamedQuery(name = "Questions.findByScore", query = "SELECT q FROM Questions q WHERE q.score = :score")})*/
class Questions implements Serializable {
	
	static final long serialVersionUID = 2L
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idquestion")
	Integer idquestion
	@Basic(optional = false)
	@Lob
	@Column(name = "answers")
	String answers
	@Column(name = "message")
	String message
	@Basic(optional = false)
	@Lob
	@Column(name = "options")
	String options
	@Basic(optional = false)
	@Column(name = "question")
	String question
	@Basic(optional = false)
	@Column(name = "type")
	String type
	@Basic(optional = false)
	@Column(name = "score")
	short score
	@JoinColumn(name = "idquiz", referencedColumnName = "idquiz")
	@ManyToOne(optional = false)
	Quiz idquiz

	def Questions() {
	}

	def Questions(Integer idquestion) {
		this.idquestion = idquestion
	}

	def Questions(Integer idquestion, String answers, String options, String question, String type, short score) {
		this.idquestion = idquestion
		this.answers = answers
		this.options = options
		this.question = question
		this.type = type
		this.score = score
	}

	@Override
	def String toString() {
		return "models.Questions[ idquestion=" + idquestion + " ]"
	}
}
