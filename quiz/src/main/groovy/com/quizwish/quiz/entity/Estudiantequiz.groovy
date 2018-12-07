package com.quizwish.quiz.entity

import com.quizwish.quiz.models.User
import java.io.Serializable
import javax.persistence.Basic
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.NamedQueries
import javax.persistence.NamedQuery
import javax.persistence.Table
import javax.xml.bind.annotation.XmlRootElement

@Entity
@Table(name = "estudiantequiz", catalog = "quiz", schema = "")
@XmlRootElement
class Estudiantequiz implements Serializable{
	
	static final long serialVersionUID = 1L
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idestudiantequiz")
	Integer idestudiantequiz
	@JoinColumn(name = "idquiz", referencedColumnName = "idquiz")
	@ManyToOne(optional = false)
	Quiz idquiz
	@JoinColumn(name = "iduser", referencedColumnName = "iduser")
	@ManyToOne(optional = false)
	User iduser

	public Estudiantequiz() {
	}

	public Estudiantequiz(Integer idestudiantequiz) {
		this.idestudiantequiz = idestudiantequiz
	}
}
