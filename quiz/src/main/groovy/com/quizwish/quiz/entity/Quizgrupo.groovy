package com.quizwish.quiz.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import java.io.Serializable
import javax.persistence.Basic
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.NamedQueries
import javax.persistence.NamedQuery
import javax.persistence.Table
import javax.xml.bind.annotation.XmlRootElement


@Entity
@Table(name = "quizgrupo", catalog = "quiz", schema = "")
@XmlRootElement
class Quizgrupo implements Serializable {

    static final long serialVersionUID = 1L
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrelacionsg")
    Integer idrelacionsg
	
    @JoinColumn(name = "idgrupo", referencedColumnName = "idgrupo")
    @ManyToOne(optional = false)
	@JsonBackReference
    Grupo idgrupo
	
    @JoinColumn(name = "idquiz", referencedColumnName = "idquiz")
    @ManyToOne(optional = false)
	@JsonBackReference
    Quiz idquiz
	
	@Column(name = "status")
	boolean status

    def Quizgrupo() {
    }

    def Quizgrupo(Integer idrelacionsg) {
        this.idrelacionsg = idrelacionsg
    }
	
	def Quizgrupo(Quiz idquiz, Grupo idgrupo, boolean status) {
		this.idquiz = idquiz
		this.idgrupo = idgrupo
		this.status = status
	}
}
