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
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.NamedQueries
import javax.persistence.NamedQuery
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlTransient


@Entity
@Table(name = "usuario", catalog = "quiz", schema = "")
@XmlRootElement
/*@NamedQueries({
	@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"), 
	@NamedQuery(name = "Usuario.findByIdusuario", query = "SELECT u FROM Usuario u WHERE u.idusuario = :idusuario")
	, @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre")
	, @NamedQuery(name = "Usuario.findByApellidos", query = "SELECT u FROM Usuario u WHERE u.apellidos = :apellidos")
	, @NamedQuery(name = "Usuario.findByTelefono", query = "SELECT u FROM Usuario u WHERE u.telefono = :telefono")
	, @NamedQuery(name = "Usuario.findByCorreo", query = "SELECT u FROM Usuario u WHERE u.correo = :correo")
	, @NamedQuery(name = "Usuario.findByPassword", query = "SELECT u FROM Usuario u WHERE u.password = :password")
	, @NamedQuery(name = "Usuario.findByPerfil", query = "SELECT u FROM Usuario u WHERE u.perfil = :perfil")})*/
class Usuario implements Serializable {
	static final long serialVersionUID = 1L
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idusuario")
	Integer idusuario
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
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
	List<Quiz> quizList
	@JoinColumn(name = "idrol", referencedColumnName = "idrol")
	@ManyToOne(optional = false)
	Roles idrol
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
	List<Grupousuario> grupousuarioList

	def Usuario() {
	}

	def Usuario(Integer idusuario) {
		this.idusuario = idusuario
	}

	def Usuario(Integer idusuario, String correo, String password) {
		this.idusuario = idusuario
		this.correo = correo
		this.password = password
	}	

	@XmlTransient
	def getGrupousuarioList() {
		return grupousuarioList
	}
	
	@XmlTransient
	def getQuizList() {
		return quizList
	}

	@Override
    def String toString() {
        return "models.Usuario[ idusuario=" + idusuario + " ]"
    }
	
	
}
