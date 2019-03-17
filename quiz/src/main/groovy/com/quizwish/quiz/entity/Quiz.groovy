package com.quizwish.quiz.entity

import java.io.Serializable
import java.time.LocalDate
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
import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.quizwish.quiz.models.User


@XmlRootElement
@Entity
@Table(name = "quiz", catalog = "quiz", schema = "")
class Quiz implements Serializable{
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idquiz")
    Integer idquiz
	
   
    @Column(name = "nombre")
    String nombre
	

    @Lob
    @Column(name = "descripcion")
    String descripcion
	
	@Column(name = "showallquestion")
	boolean  showallquestion
	
    @Column(name = "img")
    String img
	
    
    @Column(name = "mostrar")
    Integer mostrar
	
	
    @Column(name = "vista")
    boolean vista //si es true =  Todas las preguntas en una hoja And si es false= Mostrar preguntas en un wizard
	
    
    @Column(name = "random")
    boolean random

   
    @Column(name = "tiempo")
    @Temporal(TemporalType.TIME)
    Date tiempo
	
    @Column(name = "venceini")
    //@Temporal(TemporalType.DATE)
    String venceini
	
    @Column(name = "vencefin")
    //@Temporal(TemporalType.DATE)
    String vencefin
	
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
    String showfechaini
	
    @Column(name = "showfechafin")
    String  showfechafin
	
    @Column(name = "password")
    String password
	
    
    @Column(name = "estatus")
    boolean estatus

  
    @Column(name = "publicar")
    boolean publicar


	// @Basic(optional = false)
    @Column(name = "fecha")
    String fecha
	
	@ManyToOne()
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
	@JsonBackReference
    User iduser
	
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idquiz")
	@JsonManagedReference
    List<Questions> questionsList
	
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idquiz")
	@JsonManagedReference
    List<Quizgrupo> quizgrupoList
	

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idquiz")
	@JsonManagedReference
	List<Estudiantequiz> estudiantequizList
	
	


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



	public void setShowfechaini(String showfechaini) {
		this.showfechaini = showfechaini;
	}

	public void setShowfechafin(String showfechafin) {
		this.showfechafin = showfechafin;
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
	

	public boolean isShowallquestion() {
		return showallquestion;
	}

	public void setShowallquestion(boolean showallquestion) {
		this.showallquestion = showallquestion;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Integer getMostrar() {
		return mostrar;
	}

	public void setMostrar(Integer mostrar) {
		this.mostrar = mostrar;
	}

	public boolean getVista() {
		return vista;
	}

	public void setVista(boolean vista) {
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

	public  String getVenceini() {
		return venceini;
	}

	public void setVenceini(String venceini) {
		this.venceini = venceini;
	}

	public String getVencefin() {
		return vencefin;
	}

	public void setVencefin(String vencefin) {
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

	
	public boolean isPublicar() {
		 return publicar;
	 }
 
	 public void setPublicar(boolean publicar) {
		 this.publicar = publicar;
	 }
 
	 
	 /*public void setEstudiantequizList(List<Estudiantequiz> estudiantequizList) {
		 this.estudiantequizList = estudiantequizList;
	 }*/

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
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
    List<Questions>  getQuestionsList() {
        return questionsList
    }
	
    @XmlTransient
    def getQuizgrupoList() {
        return quizgrupoList
    }
	
	/*@XmlTransient
	def getEstudiantequizList() {
		return estudiantequizList
	}*/

	@Override
	def String toString() {
		return "models.Quiz[ idquiz=" + idquiz + " ]"
	}
}
