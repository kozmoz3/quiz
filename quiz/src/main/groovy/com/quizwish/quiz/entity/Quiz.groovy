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
	
    
    @Column(name = "preguntasc")
    boolean preguntasc
	
    @Column(name = "respuestac")
    boolean respuestac
	
   
    
    @Column(name = "preguntasi")
    boolean preguntasi
	
   
    @Column(name = "calificacion")
    boolean calificacion
	
    
    @Column(name = "grafico")
    boolean grafico
	
    
    @Column(name = "istiempo")
    boolean istiempo
 
    
    @Column(name = "mensajesop")
    boolean mensajesop
	
    
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
	

	public Integer getIdquiz() {
		return idquiz;
	}

	public void setIdquiz(Integer idquiz) {
		this.idquiz = idquiz;
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getMostrar() {
		return mostrar;
	}

	public void setMostrar(String mostrar) {
		this.mostrar = mostrar;
	}

	public String getVista() {
		return vista;
	}

	public void setVista(String vista) {
		this.vista = vista;
	}

	public boolean isRandom() {
		return random;
	}

	public void setRandom(boolean random) {
		this.random = random;
	}

	public Date getTiempo() {
		return tiempo;
	}

	public void setTiempo(Date tiempo) {
		this.tiempo = tiempo;
	}

	public Date getVenceini() {
		return venceini;
	}

	public void setVenceini(Date venceini) {
		this.venceini = venceini;
	}

	public Date getVencefin() {
		return vencefin;
	}

	public void setVencefin(Date vencefin) {
		this.vencefin = vencefin;
	}

	public String getIntentos() {
		return intentos;
	}

	public void setIntentos(String intentos) {
		this.intentos = intentos;
	}

	public boolean isPreguntasc() {
		return preguntasc;
	}

	public void setPreguntasc(boolean preguntasc) {
		this.preguntasc = preguntasc;
	}

	public boolean isRespuestac() {
		return respuestac;
	}

	public void setRespuestac(boolean respuestac) {
		this.respuestac = respuestac;
	}

	public boolean isPreguntasi() {
		return preguntasi;
	}

	public void setPreguntasi(boolean preguntasi) {
		this.preguntasi = preguntasi;
	}

	public boolean isCalificacion() {
		return calificacion;
	}

	public void setCalificacion(boolean calificacion) {
		this.calificacion = calificacion;
	}

	public boolean isGrafico() {
		return grafico;
	}

	public void setGrafico(boolean grafico) {
		this.grafico = grafico;
	}

	public boolean isIstiempo() {
		return istiempo;
	}

	public void setIstiempo(boolean istiempo) {
		this.istiempo = istiempo;
	}

	public boolean isMensajesop() {
		return mensajesop;
	}

	public void setMensajesop(boolean mensajesop) {
		this.mensajesop = mensajesop;
	}

	public boolean isIsintentos() {
		return isintentos;
	}

	public void setIsintentos(boolean isintentos) {
		this.isintentos = isintentos;
	}

	public Date getShowfechaini() {
		return showfechaini;
	}

	public void setShowfechaini(Date showfechaini) {
		this.showfechaini = showfechaini;
	}

	public Date getShowfechafin() {
		return showfechafin;
	}

	public void setShowfechafin(Date showfechafin) {
		this.showfechafin = showfechafin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEstatus() {
		return estatus;
	}

	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}

	public String getTipovista() {
		return tipovista;
	}

	public void setTipovista(String tipovista) {
		this.tipovista = tipovista;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public User getIduser() {
		return iduser;
	}

	public void setIduser(User iduser) {
		this.iduser = iduser;
	}

	public void setQuestionsList(List<Questions> questionsList) {
		this.questionsList = questionsList;
	}

	public void setQuizgrupoList(List<Quizgrupo> quizgrupoList) {
		this.quizgrupoList = quizgrupoList;
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
