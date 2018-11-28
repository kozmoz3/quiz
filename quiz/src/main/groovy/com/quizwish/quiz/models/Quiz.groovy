package com.quizwish.quiz.models

import java.io.Serializable
import java.util.List
import javax.persistence.Basic
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.NamedQueries
import javax.persistence.NamedQuery
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlTransient

@Entity
@Table(name = "quiz", catalog = "quiz", schema = "")
@XmlRootElement
/*@NamedQueries({
	@NamedQuery(name = "Quiz.findAll", query = "SELECT q FROM Quiz q")
	, @NamedQuery(name = "Quiz.findByIdquiz", query = "SELECT q FROM Quiz q WHERE q.idquiz = :idquiz")
	, @NamedQuery(name = "Quiz.findByNombre", query = "SELECT q FROM Quiz q WHERE q.nombre = :nombre")
	, @NamedQuery(name = "Quiz.findByDescripcion", query = "SELECT q FROM Quiz q WHERE q.descripcion = :descripcion")})*/
class Quiz implements Serializable{
	static final long serialVersionUID = 1L
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idquiz")
	Integer idquiz
	@Basic(optional = false)
	@Column(name = "nombre")
	String nombre
	@Basic(optional = false)
	@Column(name = "descripcion")
	String descripcion
	@Column(name = "img")
	String img
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idquiz")
	List<Confresultados> confresultadosList
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idquiz")
	List<Confseguridad> confseguridadList
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idquiz")
	List<Confgeneral> confgeneralList
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idquiz")
	List<Questions> questionsList

	def Quiz() {
	}

	def Quiz(Integer idquiz) {
		this.idquiz = idquiz
	}

	def Quiz(Integer idquiz, String nombre, String descripcion) {
		this.idquiz = idquiz
		this.nombre = nombre
		this.descripcion = descripcion
	}

	@XmlTransient
	def getConfresultadosList() {
		return confresultadosList
	}

	@XmlTransient
	def getConfseguridadList() {
		return confseguridadList
	}

	@XmlTransient
	def getConfgeneralList() {
		return confgeneralList
	}

	@XmlTransient
	def getQuestionsList() {
		return questionsList
	}

	@Override
	def String toString() {
		return "models.Quiz[ idquiz=" + idquiz + " ]"
	}
}
