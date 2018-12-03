package com.quizwish.quiz.models

import java.io.Serializable
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
import javax.persistence.UniqueConstraint

@Entity
@Table(name = "user", catalog = "quiz", schema = "")
class User implements Serializable {

	public User() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "iduser")
	private Integer iduser;
	
	@Column(name = "nombre", length = 30)
	private String nombre;
	
	@Column(name = "apellidos", length = 40)
	private String apellidos;
	
	@Column(name = "telefono", length = 10)
	private String telefono;
	
	@Basic(optional = false)
	@Column(name = "correo" , unique = true, nullable = false, length = 80 )
	private String correo;
	
	@Basic(optional = false)
	@Column(name = "password", nullable = false, length = 150 )
	private String password;
	
	@Column(name = "perfil", length = 255)
	private String perfil;
	
	@Column(name = "enable", nullable = false)
	private boolean enable;
	
	/*** id rol***/
	@JoinColumn(name = "idrol", referencedColumnName = "idrol")
	@ManyToOne(optional = false)
	Rol idrol

	
	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public Rol getIdrol() {
		return idrol;
	}

	public void setIdrol(Rol idrol) {
		this.idrol = idrol;
	}

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

		
}
