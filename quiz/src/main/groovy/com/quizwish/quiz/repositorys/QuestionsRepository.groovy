package com.quizwish.quiz.repositorys

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

import com.quizwish.quiz.entity.Questions

@Repository("questionsRepository")
interface QuestionsRepository extends JpaRepository<Questions,Integer>{

}
