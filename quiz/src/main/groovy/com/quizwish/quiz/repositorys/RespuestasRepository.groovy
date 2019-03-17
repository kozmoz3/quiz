package com.quizwish.quiz.repositorys

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

import com.quizwish.quiz.entity.Quiz
import com.quizwish.quiz.entity.Respuestas

@Repository("respuestasRepository")
interface RespuestasRepository extends JpaRepository<Respuestas,Integer>{
	public abstract List<Respuestas> findAllByIdquiz(Quiz idquiz);
}
