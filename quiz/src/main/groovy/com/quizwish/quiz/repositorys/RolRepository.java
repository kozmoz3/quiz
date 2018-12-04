package com.quizwish.quiz.repositorys;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quizwish.quiz.models.Rol;


@Repository("rolRepository")
public interface RolRepository extends JpaRepository<Rol, Serializable>{

	public abstract Rol findByIdrol(Integer idrol);
}
