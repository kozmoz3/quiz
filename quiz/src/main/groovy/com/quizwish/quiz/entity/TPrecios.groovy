package com.quizwish.quiz.entity

import javax.persistence.Basic
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.CascadeType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.persistence.Temporal
import javax.persistence.TemporalType
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement
@Entity
@Table(name = "tprecios", catalog = "quiz", schema = "")
class TPrecios {
	
	static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idprecio")
    Integer idprecio;
    @Basic(optional = false)
    @Column(name = "nombre")
    String nombre;
    @Basic(optional = false)
    @Column(name = "descripcion")
    String descripcion;
    @Basic(optional = false)
    @Column(name = "precio")
    double precio;
    @Basic(optional = false)
    @Column(name = "fechault")
    @Temporal(TemporalType.TIMESTAMP)
    Date fechault;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idprecio")
    List<Contrato> contratoList;

    def Tprecios() {
    }

    def Tprecios(Integer idprecio) {
        this.idprecio = idprecio;
    }

    def Tprecios(Integer idprecio, String nombre, String descripcion, double precio, Date fechault) {
        this.idprecio = idprecio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fechault = fechault;
    }
}
