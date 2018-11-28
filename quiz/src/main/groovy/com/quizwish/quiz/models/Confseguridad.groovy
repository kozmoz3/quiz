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
    @JoinColumn(name = "idquiz", referencedColumnName = "idquiz")
    @ManyToOne(optional = false)
    Quiz idquiz
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idconfseguri")
    List<Seguridadgrupo> seguridadgrupoList

	def Confseguridad() {
	}

	def Confseguridad(Integer idconfseguri) {
		this.idconfseguri = idconfseguri
	}
	
	@XmlTransient
	def getSeguridadgrupoList() {
		return seguridadgrupoList
	}
	

	@Override
	def String toString() {
		return "models.Confseguridad[ idconfseguri=" + idconfseguri + " ]"
	}
}
