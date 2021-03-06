package com.quizwish.quiz.entity

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

import com.fasterxml.jackson.annotation.JsonBackReference
import com.quizwish.quiz.models.User

@Entity
@Table(name = "grupouser", catalog = "quiz", schema = "")
@XmlRootElement
/*@NamedQueries({
	@NamedQuery(name = "Grupousuario.findAll", query = "SELECT g FROM Grupousuario g")
	, @NamedQuery(name = "Grupousuario.findByIdrelaciongu", query = "SELECT g FROM Grupousuario g WHERE g.idrelaciongu = :idrelaciongu")})*/
class Grupousuario implements Serializable{
	static final long serialVersionUID = 1L
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idrelaciongu")
	Integer idrelaciongu
	
	@Basic(optional = false)
	@Column(name = "idstudent")
	Integer idstudent
	
	@Basic(optional = false)
	@Column(name = "estatus")
	boolean estatus
	
	@JoinColumn(name = "idgrupo", referencedColumnName = "idgrupo")
	@ManyToOne(optional = false)
	@JsonBackReference
	Grupo idgrupo
	
	@JoinColumn(name = "iduser", referencedColumnName = "iduser")
	@ManyToOne(optional = false)
	@JsonBackReference
	User iduser

	def Grupousuario() {
	}

	def Grupousuario(Integer idrelaciongu) {
		this.idrelaciongu = idrelaciongu
	}
	def Grupousuario(Integer idstudent, boolean estatus, User iduser, Grupo idgrupo) {
		this.idstudent = idstudent
		this.estatus = estatus
		this.iduser = iduser
		this.idgrupo = idgrupo
	}
	
	def Grupousuario(Integer idstudent, boolean estatus) {
		this.idstudent = idstudent
		this.estatus = estatus
	}

	@Override
	def String toString() {
		return "models.Grupousuario[ idrelaciongu=" + idrelaciongu + " ]"
	}
}
