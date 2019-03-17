package com.quizwish.quiz.entity

import javax.persistence.Basic
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.xml.bind.annotation.XmlRootElement

import com.fasterxml.jackson.annotation.JsonBackReference

@Entity
@Table(name = "respuestas", catalog = "quiz", schema = "")
@XmlRootElement
class Respuestas implements Serializable{
	static final long serialVersionUID = 1L
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idexamen")
	Integer idexamen
	@Basic(optional = false)
	@Column(name = "prespuestas")
	String prespuestas
	@JoinColumn(name = "idquiz", referencedColumnName = "idquiz")
	@ManyToOne(optional = false)
	@JsonBackReference
	Quiz idquiz
	@Basic(optional = false)
	@Column(name = "status")
	boolean status
	@Basic(optional = false)
	@Column(name = "fecha")
	Date fecha
	@Basic(optional = false)
	@Column(name = "calificacion")
	double calificacion
	@Column(name = "idstudent")
	Integer idStudent
	@Basic(optional = false)
	@Column(name = "correctas")
	double correctas
	@Basic(optional = false)
	@Column(name = "incorrectas")
	double incorrectas


	public Respuestas() {}

	public Respuestas(Integer idexamen) {
		this.idexamen = idexamen;
	}

	public Respuestas(Integer idexamen, String prespuestas, Quiz idquiz, boolean status, Date fecha,
	double calificacion, Integer idStudent, double correctas, double incorrectas) {
		super();
		this.idexamen = idexamen;
		this.prespuestas = prespuestas;
		this.idquiz = idquiz;
		this.status = status;
		this.fecha = fecha;
		this.calificacion = calificacion;
		this.idStudent = idStudent
		this.correctas=correctas
		this.incorrectas=incorrectas
	}

	public Respuestas(String prespuestas, Quiz idquiz, boolean status, Date fecha, double calificacion,Integer idStudent, double correctas, double incorrectas) {
		super();
		this.prespuestas = prespuestas;
		this.idquiz = idquiz;
		this.status = status;
		this.fecha = fecha;
		this.calificacion = calificacion;
		this.idStudent = idStudent
		this.correctas=correctas
		this.incorrectas=incorrectas
	}

	@Override
	public String toString() {
		return "Respuestas [idexamen=" + idexamen + "]";
	}
}
