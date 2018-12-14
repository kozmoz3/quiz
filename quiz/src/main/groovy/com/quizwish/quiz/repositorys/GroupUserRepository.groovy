package com.quizwish.quiz.repositorys

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

import com.quizwish.quiz.entity.Grupousuario

@Repository("groupuserRepository")
interface GroupUserRepository extends JpaRepository< Grupousuario, Integer >{

}
