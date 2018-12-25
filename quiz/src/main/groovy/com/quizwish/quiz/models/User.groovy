package com.quizwish.quiz.models

import com.quizwish.quiz.entity.Grupo
import com.quizwish.quiz.entity.Grupousuario
import com.quizwish.quiz.entity.Quiz
import java.io.Serializable
import javax.persistence.Basic
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.persistence.UniqueConstraint
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.xml.bind.annotation.XmlTransient

import org.hibernate.validator.constraints.Length

@Entity
@Table(name = "user", catalog = "quiz", schema = "")
class User implements Serializable {

	public User() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "iduser")
	private Integer iduser;
	
	@NotEmpty
	@Length(min=4, max=30)
	@Column(name = "nombre", length = 30)
	private String nombre;
	
	@Length(min=4, max=40)
	@Column(name = "apellidos", length = 40)
	private String apellidos;
	
	@Length(min=5, max=10)
	@Column(name = "telefono", length = 10)
	private String telefono;
	
	
	@Email
	@Length(min=7, max=80)
	@Column(name = "correo" , unique = true, nullable = false, length = 80 )
	private String correo;
	
	@NotEmpty
	@Length(min=4, max=60)
	@Column(name = "username",  length = 60 )
	private String username;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "iduser")
	private List<Quiz> quizList;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "iduser")
	private List<Grupousuario> grupousuarioList;
	
	/*@OneToMany(cascade = CascadeType.ALL, mappedBy = "iduser")
	private List<Estudiantequiz> estudiantequizList*/
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "iduser")
	private List<Grupo> grupList
	
	
	
	public List<Grupo> getGrupList() {
		return grupList;
	}

	public void setGrupList(List<Grupo> grupList) {
		this.grupList = grupList;
	}

	public User(String correo) {
		this.correo = correo
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	@Length(max=150)
	@Column(name = "password", nullable = false, length = 150 )
	private String password;
	
	@Column(name = "perfil", length = 255)
	private String perfil;
	
	@Column(name = "enable", nullable = false)
	private boolean enable;
	
	/*** id rol***/
	@JoinColumn(name = "idrol", referencedColumnName = "idrol")
	@ManyToOne()
	Rol idrol
	public int getIdrol() {
		return this.idrol.getIdrol();
	}
	
	public String getRol() {
		return this.idrol.getDescripcion();
	}

	public void setIdrol(Rol idrol) {
		this.idrol = idrol;
	}
	
	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	

	public Integer getIduser() {
		return iduser;
	}

	public void setIduser(Integer iduser) {
		this.iduser = iduser;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	@XmlTransient
    public List<Quiz> getQuizList() {
        return quizList;
    }

    public void setQuizList(List<Quiz> quizList) {
        this.quizList = quizList;
    }

	 @XmlTransient
    public List<Grupousuario> getGrupousuarioList() {
        return grupousuarioList;
    }

	public void setGrupousuarioList(List<Grupousuario> grupousuarioList) {
		this.grupousuarioList = grupousuarioList;
	}
	
	/*@XmlTransient
	public List<Estudiantequiz> getEstudiantequizList() {
		return estudiantequizList;
	}

	public void setEstudiantequizList(List<Estudiantequiz> estudiantequizList) {
		this.estudiantequizList = estudiantequizList;
	}*/

	@Override
	public String toString() {
		return "User [iduser=" + iduser + ", correo=" + correo+", nombre=" + nombre + ", apellidos=" + apellidos + ", telefono=" + telefono + ", correo=" + correo + ", username=" + username +  ", password=" + password + ", enable=" + enable + ", idrol=" + idrol + "]";
	}

		
}
