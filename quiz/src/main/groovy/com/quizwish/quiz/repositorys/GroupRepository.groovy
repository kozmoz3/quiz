package com.quizwish.quiz.repositorys

import com.quizwish.quiz.entity.Grupo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository("grupoRepository")
interface GroupRepository extends JpaRepository<Grupo,Integer>{

}
