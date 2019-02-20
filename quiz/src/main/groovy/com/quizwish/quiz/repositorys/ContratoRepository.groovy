package com.quizwish.quiz.repositorys

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

import com.quizwish.quiz.entity.Contrato

@Repository("contratoRepository")
public interface ContratoRepository extends JpaRepository<Contrato,String>{

}
