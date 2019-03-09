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
import javax.persistence.Temporal
import javax.persistence.TemporalType
import javax.xml.bind.annotation.XmlRootElement

import com.fasterxml.jackson.annotation.JsonBackReference
import com.quizwish.quiz.models.User

@XmlRootElement
@Entity
@Table(name = "contrato", catalog = "quiz", schema = "")
class Contrato implements Serializable{
	
	static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idcontrato")
    String idcontrato;
    @Basic(optional = false)
    @Column(name = "fechacontra")
    @Temporal(TemporalType.TIMESTAMP)
    Date fechacontra;
    @Basic(optional = false)
    @Column(name = "fechavence")
    @Temporal(TemporalType.TIMESTAMP)
    Date fechavence;
    @Basic(optional = false)
    @Column(name = "estatus")
    boolean estatus;
    @JoinColumn(name = "idprecio", referencedColumnName = "idprecio")
    @ManyToOne(optional = false)
	@JsonBackReference
    TPrecios idprecio;
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    @ManyToOne(optional = false)
	@JsonBackReference
    User iduser;

    def Contrato() {
    }

    def Contrato(String idcontrato) {
        this.idcontrato = idcontrato;
    }

    def Contrato(String idcontrato, Date fechacontra, Date fechavence, boolean estatus) {
        this.idcontrato = idcontrato;
        this.fechacontra = fechacontra;
        this.fechavence = fechavence;
        this.estatus = estatus;
    }
}
