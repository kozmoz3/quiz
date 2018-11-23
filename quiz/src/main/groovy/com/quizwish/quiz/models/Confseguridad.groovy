package com.quizwish.quiz.models

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
@Table(name = "confseguridad", catalog = "quiz", schema = "")
@XmlRootElement
/*@NamedQueries({
	@NamedQuery(name = "Confseguridad.findAll", query = "SELECT c FROM Confseguridad c")
	, @NamedQuery(name = "Confseguridad.findByIdconfseguri", query = "SELECT c FROM Confseguridad c WHERE c.idconfseguri = :idconfseguri")
	, @NamedQuery(name = "Confseguridad.findByPassword", query = "SELECT c FROM Confseguridad c WHERE c.password = :password")})*/
class Confseguridad implements Serializable {
	static final long serialVersionUID = 1L
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idconfseguri")
	Integer idconfseguri
	@Column(name = "password")
	String password
	@JoinColumn(name = "idgrupo", referencedColumnName = "idgrupo")
	@ManyToOne
	Grupo idgrupo
	@JoinColumn(name = "idquiz", referencedColumnName = "idquiz")
	@ManyToOne(optional = false)
	Quiz idquiz

	def Confseguridad() {
	}

	def Confseguridad(Integer idconfseguri) {
		this.idconfseguri = idconfseguri
	}

	def Integer getIdconfseguri() {
		return idconfseguri
	}

	def setIdconfseguri(Integer idconfseguri) {
		this.idconfseguri = idconfseguri
	}

	def String getPassword() {
		return password
	}

	def setPassword(String password) {
		this.password = password
	}

	def Grupo getIdgrupo() {
		return idgrupo
	}

	def setIdgrupo(Grupo idgrupo) {
		this.idgrupo = idgrupo
	}

	def Quiz getIdquiz() {
		return idquiz
	}

	def setIdquiz(Quiz idquiz) {
		this.idquiz = idquiz
	}

	@Override
	def String toString() {
		return "models.Confseguridad[ idconfseguri=" + idconfseguri + " ]"
	}
}
