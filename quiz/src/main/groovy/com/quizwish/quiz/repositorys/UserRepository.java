package com.quizwish.quiz.repositorys;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quizwish.quiz.models.User;

/***Repositorio para autentificacion**/

@Repository("userRepository")
public interface UserRepository  extends JpaRepository<User, Serializable>{

	public abstract User findByCorreo(String correo);
	
}
