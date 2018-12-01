package com.quizwish.quiz.models

import javax.persistence.Basic
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "user", catalog = "quiz", schema = "")
class User implements Serializable {

	public User() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "iduser")
	Integer iduser
	
	@Column(name = "nombre")
	String nombre
	
	@Column(name = "apellidos")
	String apellidos
	
	@Column(name = "telefono")
	String telefono
	
	@Basic(optional = false)
	@Column(name = "correo")
	String correo
	
	@Basic(optional = false)
	@Column(name = "password")
	String password
	
	@Column(name = "perfil")
	String perfil
	
	/*** id rol***/
	@JoinColumn(name = "idrol", referencedColumnName = "idrol")
	@ManyToOne(optional = false)
	Roles idrol

	public Integer getIduser() {
		return iduser;
	}

	public void setIduser(Integer iduser) {
		this.iduser = iduser;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public Roles getIdrol() {
		return idrol;
	}

	public void setIdrol(Roles idrol) {
		this.idrol = idrol;
	}
	
	/*@OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
	List<Quiz> quizList
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
	List<Grupousuario> grupousuarioList*/
	
	
}
