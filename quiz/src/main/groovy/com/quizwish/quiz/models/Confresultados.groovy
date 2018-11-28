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
@Table(name = "confresultados", catalog = "quiz", schema = "")
@XmlRootElement
/*@NamedQueries({
	@NamedQuery(name = "Confresultados.findAll", query = "SELECT c FROM Confresultados c")
	, @NamedQuery(name = "Confresultados.findByIdconfresult", query = "SELECT c FROM Confresultados c WHERE c.idconfresult = :idconfresult")
	, @NamedQuery(name = "Confresultados.findByIntentos", query = "SELECT c FROM Confresultados c WHERE c.intentos = :intentos")
	, @NamedQuery(name = "Confresultados.findByShowfechaini", query = "SELECT c FROM Confresultados c WHERE c.showfechaini = :showfechaini")
	, @NamedQuery(name = "Confresultados.findByShowfechafin", query = "SELECT c FROM Confresultados c WHERE c.showfechafin = :showfechafin")})*/
class Confresultados implements Serializable{
	static final long serialVersionUID = 1L
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idconfresult")
	Integer idconfresult
	@Basic(optional = false)
	@Lob
	@Column(name = "preguntasc")
	boolean preguntasc
	@Basic(optional = false)
	@Lob
	@Column(name = "respuestac")
	boolean respuestac
	@Basic(optional = false)
	@Lob
	@Column(name = "preguntasi")
	boolean preguntasi
	@Basic(optional = false)
	@Lob
	@Column(name = "calificacion")
	boolean calificacion
	@Basic(optional = false)
	@Lob
	@Column(name = "grafico")
	boolean grafico
	@Basic(optional = false)
	@Lob
	@Column(name = "tiempo")
	boolean tiempo
	@Basic(optional = false)
	@Lob
	@Column(name = "mensajesop")
	boolean mensajesop
	@Basic(optional = false)
	boolean intentos
	@Column(name = "showfechaini")
	@Temporal(TemporalType.DATE)
	Date showfechaini
	@Column(name = "showfechafin")
	@Temporal(TemporalType.DATE)
	Date showfechafin
	@JoinColumn(name = "idquiz", referencedColumnName = "idquiz")
	@ManyToOne(optional = false)
	Quiz idquiz

	def Confresultados() {
	}

	def Confresultados(Integer idconfresult) {
		this.idconfresult = idconfresult
	}

	def Confresultados(Integer idconfresult, boolean preguntasc, boolean respuestac, boolean preguntasi, boolean calificacion, boolean grafico, boolean tiempo, boolean mensajesop, boolean intentos) {
		this.idconfresult = idconfresult
		this.preguntasc = preguntasc
		this.respuestac = respuestac
		this.preguntasi = preguntasi
		this.calificacion = calificacion
		this.grafico = grafico
		this.tiempo = tiempo
		this.mensajesop = mensajesop
		this.intentos = intentos
	}	

	@Override
	def String toString() {
		return "models.Confresultados[ idconfresult=" + idconfresult + " ]"
	}
}
