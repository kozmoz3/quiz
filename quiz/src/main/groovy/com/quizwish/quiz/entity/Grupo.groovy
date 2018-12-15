package com.quizwish.quiz.entity

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

import com.quizwish.quiz.models.User

/**
 *
 * @author Alfonso
 */

@XmlRootElement
/*@NamedQueries({
 @NamedQuery(name = "Grupo.findAll", query = "SELECT g FROM Grupo g")
 , @NamedQuery(name = "Grupo.findByIdgrupo", query = "SELECT g FROM Grupo g WHERE g.idgrupo = :idgrupo")
 , @NamedQuery(name = "Grupo.findByNombre", query = "SELECT g FROM Grupo g WHERE g.nombre = :nombre")
 , @NamedQuery(name = "Grupo.findByDescripcion", query = "SELECT g FROM Grupo g WHERE g.descripcion = :descripcion")})*/

@Entity
@Table(name = "grupo", catalog = "quiz", schema = "")
class Grupo implements Serializable{

	def Grupo() {
	}

	def Grupo(Integer idgrupo) {
		this.idgrupo = idgrupo
	}

	def Grupo(Integer idgrupo, String nombre, String descripcion) {
		this.idgrupo = idgrupo
		this.nombre = nombre
		this.descripcion = descripcion
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idgrupo")
	Integer idgrupo

	@Basic(optional = false)
	@Column(name = "nombre")
	String nombre

	@Basic(optional = false)
	@Column(name = "descripcion")
	String descripcion

	@JoinColumn(name = "iduser", referencedColumnName = "iduser")
	@ManyToOne()
	User iduser
	

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idgrupo")
	List<Quizgrupo> quizgrupoList

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idgrupo")
	List<Grupousuario> grupousuarioList

	/*fin getter and setter*/

	public Integer getIdgrupo() {
		return idgrupo;
	}

	public void setIdgrupo(Integer idgrupo) {
		this.idgrupo = idgrupo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public User getUser() {
		return iduser;
	}

	public void setUser(User iduser) {
		this.iduser = iduser;
	}

	public int getIdUser() {
		return iduser.getIduser();
	}

	public void setQuizgrupoList(List<Quizgrupo> quizgrupoList) {
		this.quizgrupoList = quizgrupoList;
	}

	public void setGrupousuarioList(List<Grupousuario> grupousuarioList) {
		this.grupousuarioList = grupousuarioList;
	}


	@XmlTransient
	def getQuizgrupoList() {
		return quizgrupoList
	}

	@XmlTransient
	def getGrupousuarioList() {
		return grupousuarioList
	}

	@Override
	def String toString() {
		return "models.Grupo[ idgrupo=" + idgrupo + " ]"
	}
}
