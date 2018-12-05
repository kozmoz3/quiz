package com.quizwish.quiz.entity

import java.io.Serializable
import java.util.Date
import java.util.List
import javax.persistence.Basic
import javax.persistence.CascadeType
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
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.persistence.Temporal
import javax.persistence.TemporalType
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlTransient

import com.quizwish.quiz.models.User


@XmlRootElement
/*@NamedQueries({
    @NamedQuery(name = "Quiz.findAll", query = "SELECT q FROM Quiz q")
    , @NamedQuery(name = "Quiz.findByIdquiz", query = "SELECT q FROM Quiz q WHERE q.idquiz = :idquiz")
    , @NamedQuery(name = "Quiz.findByNombre", query = "SELECT q FROM Quiz q WHERE q.nombre = :nombre")
    , @NamedQuery(name = "Quiz.findByImg", query = "SELECT q FROM Quiz q WHERE q.img = :img")
    , @NamedQuery(name = "Quiz.findByMostrar", query = "SELECT q FROM Quiz q WHERE q.mostrar = :mostrar")
    , @NamedQuery(name = "Quiz.findByVista", query = "SELECT q FROM Quiz q WHERE q.vista = :vista")
    , @NamedQuery(name = "Quiz.findByTiempo", query = "SELECT q FROM Quiz q WHERE q.tiempo = :tiempo")
    , @NamedQuery(name = "Quiz.findByVenceini", query = "SELECT q FROM Quiz q WHERE q.venceini = :venceini")
    , @NamedQuery(name = "Quiz.findByVencefin", query = "SELECT q FROM Quiz q WHERE q.vencefin = :vencefin")
    , @NamedQuery(name = "Quiz.findByIntentos", query = "SELECT q FROM Quiz q WHERE q.intentos = :intentos")
    , @NamedQuery(name = "Quiz.findByShowfechaini", query = "SELECT q FROM Quiz q WHERE q.showfechaini = :showfechaini")
    , @NamedQuery(name = "Quiz.findByShowfechafin", query = "SELECT q FROM Quiz q WHERE q.showfechafin = :showfechafin")
    , @NamedQuery(name = "Quiz.findByPassword", query = "SELECT q FROM Quiz q WHERE q.password = :password")
    , @NamedQuery(name = "Quiz.findByTipovista", query = "SELECT q FROM Quiz q WHERE q.tipovista = :tipovista")
    , @NamedQuery(name = "Quiz.findByFecha", query = "SELECT q FROM Quiz q WHERE q.fecha = :fecha")})*/

@Entity
@Table(name = "quiz", catalog = "quiz", schema = "")
class Quiz implements Serializable{
	
	static final long serialVersionUID = 1L
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idquiz")
    Integer idquiz
	
    @Basic(optional = false)
    @Column(name = "nombre")
    String nombre
	
    @Basic(optional = false)
    @Lob
    @Column(name = "descripcion")
    String descripcion
	
    @Column(name = "img")
    String img
	
    @Basic(optional = false)
    @Column(name = "mostrar")
    String mostrar
	
    @Basic(optional = false)
    @Column(name = "vista")
    String vista
	
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
	
    @Column(name = "intentos")
    String intentos
	
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
    @Column(name = "istiempo")
    boolean istiempo
    @Basic(optional = false)
    @Lob
    @Column(name = "mensajesop")
    boolean mensajesop
    @Basic(optional = false)
    @Lob
    @Column(name = "isintentos")
    boolean isintentos

    @Column(name = "showfechaini")
    @Temporal(TemporalType.DATE)
    Date showfechaini
	
    @Column(name = "showfechafin")
    @Temporal(TemporalType.DATE)
    Date showfechafin
	
    @Column(name = "password")
    String password
	
    @Basic(optional = false)
    @Lob
    @Column(name = "estatus")
    boolean estatus

    @Basic(optional = false)
    @Column(name = "tipovista")
    String tipovista
	
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    Date fecha
	
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    @ManyToOne(optional = false)
    User iduser
	
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idquiz")
    List<Questions> questionsList
	
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idquiz")
    List<Quizgrupo> quizgrupoList

    public Quiz() {
    }

    public Quiz(Integer idquiz) {
        this.idquiz = idquiz
    }

    public Quiz(Integer idquiz, String nombre, String descripcion, String mostrar, String vista, boolean random, Date tiempo, boolean preguntasc, boolean respuestac, boolean preguntasi, boolean calificacion, boolean grafico, boolean istiempo, boolean mensajesop, boolean isintentos, boolean estatus, String tipovista, Date fecha) {
        this.idquiz = idquiz
        this.nombre = nombre
        this.descripcion = descripcion
        this.mostrar = mostrar
        this.vista = vista
        this.random = random
        this.tiempo = tiempo
        this.preguntasc = preguntasc
        this.respuestac = respuestac
        this.preguntasi = preguntasi
        this.calificacion = calificacion
        this.grafico = grafico
        this.istiempo = istiempo
        this.mensajesop = mensajesop
        this.isintentos = isintentos
        this.estatus = estatus
        this.tipovista = tipovista
        this.fecha = fecha
    }
	
	public Quiz(String nombre, String descripcion, String mostrar, String vista, boolean random, Date tiempo, boolean preguntasc, boolean respuestac, boolean preguntasi, boolean calificacion, boolean grafico, boolean istiempo, boolean mensajesop, boolean isintentos, boolean estatus, String tipovista, Date fecha) {
		this.idquiz = idquiz
		this.nombre = nombre
		this.descripcion = descripcion
		this.mostrar = mostrar
		this.vista = vista
		this.random = random
		this.tiempo = tiempo
		this.preguntasc = preguntasc
		this.respuestac = respuestac
		this.preguntasi = preguntasi
		this.calificacion = calificacion
		this.grafico = grafico
		this.istiempo = istiempo
		this.mensajesop = mensajesop
		this.isintentos = isintentos
		this.estatus = estatus
		this.tipovista = tipovista
		this.fecha = fecha
	}

	@XmlTransient
    def getQuestionsList() {
        return questionsList
    }
	
    @XmlTransient
    def getQuizgrupoList() {
        return quizgrupoList
    }

	@Override
	def String toString() {
		return "models.Quiz[ idquiz=" + idquiz + " ]"
	}
}
