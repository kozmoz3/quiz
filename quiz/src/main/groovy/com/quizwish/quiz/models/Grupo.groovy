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

/**
 *
 * @author Alfonso
 */
@Entity
@Table(name = "grupo", catalog = "quiz", schema = "")
@XmlRootElement
/*@NamedQueries({
	@NamedQuery(name = "Grupo.findAll", query = "SELECT g FROM Grupo g")
	, @NamedQuery(name = "Grupo.findByIdgrupo", query = "SELECT g FROM Grupo g WHERE g.idgrupo = :idgrupo")
	, @NamedQuery(name = "Grupo.findByNombre", query = "SELECT g FROM Grupo g WHERE g.nombre = :nombre")
	, @NamedQuery(name = "Grupo.findByDescripcion", query = "SELECT g FROM Grupo g WHERE g.descripcion = :descripcion")})*/
class Grupo implements Serializable{
	static final long serialVersionUID = 1L;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idgrupo")
    List<Seguridadgrupo> seguridadgrupoList
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idgrupo")
    List<Grupousuario> grupousuarioList

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

	@XmlTransient
    def getSeguridadgrupoList() {
        return seguridadgrupoList
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
