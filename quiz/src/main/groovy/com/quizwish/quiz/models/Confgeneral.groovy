package com.quizwish.quiz.models

import java.io.Serializable
import java.util.Date
import javax.persistence.Basic
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.Lob
import javax.persistence.ManyToOne
import javax.persistence.NamedQueries
import javax.persistence.NamedQuery
import javax.persistence.Table
import javax.persistence.Temporal
import javax.persistence.TemporalType
import javax.xml.bind.annotation.XmlRootElement

@Entity
@Table(name = "confgeneral", catalog = "quiz", schema = "")
@XmlRootElement
/*@NamedQueries({
	@NamedQuery(name = "Confgeneral.findAll", query = "SELECT c FROM Confgeneral c")
	, @NamedQuery(name = "Confgeneral.findByIdconfgen", query = "SELECT c FROM Confgeneral c WHERE c.idconfgen = :idconfgen")
	, @NamedQuery(name = "Confgeneral.findByMostrar", query = "SELECT c FROM Confgeneral c WHERE c.mostrar = :mostrar")
	, @NamedQuery(name = "Confgeneral.findByVista", query = "SELECT c FROM Confgeneral c WHERE c.vista = :vista")
	, @NamedQuery(name = "Confgeneral.findByTiempo", query = "SELECT c FROM Confgeneral c WHERE c.tiempo = :tiempo")
	, @NamedQuery(name = "Confgeneral.findByVenceini", query = "SELECT c FROM Confgeneral c WHERE c.venceini = :venceini")
	, @NamedQuery(name = "Confgeneral.findByVencefin", query = "SELECT c FROM Confgeneral c WHERE c.vencefin = :vencefin")})*/
class Confgeneral implements Serializable{
	static final long serialVersionUID = 1L
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idconfgen")
	Integer idconfgen
	@Basic(optional = false)
	@Column(name = "mostrar")
	String mostrar
	@Basic(optional = false)
	@Column(name = "vista")
	String vista
	@Basic(optional = false)
	@Column(name = "intentos")
	String intentos
	@Basic(optional = false)
	@Lob
	@Column(name = "random")
	boolean random
	@Basic(optional = false)
	@Column(name = "tiempo")
	@Temporal(TemporalType.TIME)
	Date tiempo
	@Column(name = "venceini")
	@Temporal(TemporalType.DATE)
	Date venceini
	@Column(name = "vencefin")
	@Temporal(TemporalType.DATE)
	Date vencefin
	@JoinColumn(name = "idquiz", referencedColumnName = "idquiz")
	@ManyToOne(optional = false)
	Quiz idquiz

	def Confgeneral() {
	}

	def Confgeneral(Integer idconfgen) {
		this.idconfgen = idconfgen
	}

	def Confgeneral(Integer idconfgen, String mostrar, String vista,String intentos, boolean random, Date tiempo) {
		this.idconfgen = idconfgen
		this.mostrar = mostrar
		this.vista = vista
		this.random = random
		this.tiempo = tiempo
		this.intentos = intentos;
	}

	def Integer getIdconfgen() {
		return idconfgen
	}

	def setIdconfgen(Integer idconfgen) {
		this.idconfgen = idconfgen
	}

	def String getMostrar() {
		return mostrar
	}

	def setMostrar(String mostrar) {
		this.mostrar = mostrar
	}

	def String getVista() {
		return vista
	}

	def setVista(String vista) {
		this.vista = vista
	}
	
	def String getIntentos() {
		return intentos
	}

	def setIntentos(String intentos) {
		this.intentos = intentos
	}

	def boolean getRandom() {
		return random
	}

	def setRandom(boolean random) {
		this.random = random
	}

	def Date getTiempo() {
		return tiempo
	}

	def setTiempo(Date tiempo) {
		this.tiempo = tiempo
	}

	def Date getVenceini() {
		return venceini
	}

	def setVenceini(Date venceini) {
		this.venceini = venceini
	}

	def Date getVencefin() {
		return vencefin
	}

	def setVencefin(Date vencefin) {
		this.vencefin = vencefin
	}

	def Quiz getIdquiz() {
		return idquiz
	}

	def setIdquiz(Quiz idquiz) {
		this.idquiz = idquiz
	}

	@Override
	def String toString() {
		return "models.Confgeneral[ idconfgen=" + idconfgen + " ]"
	}
}
