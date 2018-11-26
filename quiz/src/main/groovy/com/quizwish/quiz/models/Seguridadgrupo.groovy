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
@Table(name = "seguridadgrupo", catalog = "quiz", schema = "")
@XmlRootElement
/*@NamedQueries({
	@NamedQuery(name = "Seguridadgrupo.findAll", query = "SELECT s FROM Seguridadgrupo s")
	, @NamedQuery(name = "Seguridadgrupo.findByIdrelacionsg", query = "SELECT s FROM Seguridadgrupo s WHERE s.idrelacionsg = :idrelacionsg")})*/
class Seguridadgrupo {
	static final long serialVersionUID = 1L
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idrelacionsg")
	Integer idrelacionsg
	@JoinColumn(name = "idgrupo", referencedColumnName = "idgrupo")
	@ManyToOne(optional = false)
	Grupo idgrupo
	@JoinColumn(name = "idconfseguri", referencedColumnName = "idconfseguri")
	@ManyToOne(optional = false)
	Confseguridad idconfseguri

	def Seguridadgrupo() {
	}

	def Seguridadgrupo(Integer idrelacionsg) {
		this.idrelacionsg = idrelacionsg
	}

	def Integer getIdrelacionsg() {
		return idrelacionsg
	}

	def setIdrelacionsg(Integer idrelacionsg) {
		this.idrelacionsg = idrelacionsg
	}

	def Grupo getIdgrupo() {
		return idgrupo
	}

	def setIdgrupo(Grupo idgrupo) {
		this.idgrupo = idgrupo
	}

	def Confseguridad getIdconfseguri() {
		return idconfseguri
	}

	def setIdconfseguri(Confseguridad idconfseguri) {
		this.idconfseguri = idconfseguri
	}

	@Override
	def String toString() {
		return "Seguridadgrupo [idrelacionsg=" + idrelacionsg + ", idgrupo=" + idgrupo + ", idconfseguri="
				+ idconfseguri + "]"
	}
	
}
