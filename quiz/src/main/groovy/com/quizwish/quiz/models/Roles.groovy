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
@Table(name = "roles", catalog = "quiz", schema = "")
@XmlRootElement
/*@NamedQueries({
	@NamedQuery(name = "Roles.findAll", query = "SELECT r FROM Roles r")
	, @NamedQuery(name = "Roles.findByIdrol", query = "SELECT r FROM Roles r WHERE r.idrol = :idrol")
	, @NamedQuery(name = "Roles.findByDescripcion", query = "SELECT r FROM Roles r WHERE r.descripcion = :descripcion")})*/
class Roles implements Serializable{
	static final long serialVersionUID = 1L
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idrol")
	Integer idrol
	@Basic(optional = false)
	@Column(name = "descripcion")
	String descripcion
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idrol")
	List<Usuario> usuarioList

	def Roles() {
	}

	def Roles(Integer idrol) {
		this.idrol = idrol
	}

	def Roles(Integer idrol, String descripcion) {
		this.idrol = idrol
		this.descripcion = descripcion
	}

	def Integer getIdrol() {
		return idrol
	}

	def setIdrol(Integer idrol) {
		this.idrol = idrol
	}

	def String getDescripcion() {
		return descripcion
	}

	def setDescripcion(String descripcion) {
		this.descripcion = descripcion
	}

	@XmlTransient
	def List<Usuario> getUsuarioList() {
		return usuarioList
	}

	def setUsuarioList(List<Usuario> usuarioList) {
		this.usuarioList = usuarioList
	}
	
	@Override
	def String toString() {
		return "models.Roles[ idrol=" + idrol + " ]"
	}
}
