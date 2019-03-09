package com.quizwish.quiz.models

import javax.persistence.Basic
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

import com.fasterxml.jackson.annotation.JsonManagedReference

@Entity
@Table(name = "roles", catalog = "quiz", schema = "")
class Rol implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idrol")
	private Integer idrol
	
	@Basic(optional = false)
	@Column(name = "descripcion")
	String descripcion

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idrol")
	@JsonManagedReference
	List<User> usuarioList
	
	public Rol() {
		super();
	}

	
	public Rol(String descripcion, List<User> usuarioList) {
		super();
		this.descripcion = descripcion;
		this.usuarioList = usuarioList;
	}


	public Integer getIdrol() {
		return idrol;
	}

	public void setIdrol(Integer idrol) {
		this.idrol = idrol;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public List<User> getUsuarioList() {
		return usuarioList;
	}


	public void setUsuarioList(List<User> usuarioList) {
		this.usuarioList = usuarioList;
	}

		
}
