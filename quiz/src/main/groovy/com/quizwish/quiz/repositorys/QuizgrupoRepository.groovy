package com.quizwish.quiz.repositorys

import com.quizwish.quiz.entity.Quizgrupo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository("quizgrupoRepository")
interface QuizgrupoRepository extends JpaRepository<Quizgrupo, Integer>{

}
