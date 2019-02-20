package com.quizwish.quiz.repositorys

import com.quizwish.quiz.entity.TPrecios
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository("tpreciosRepository")
public interface TPreciosRepository extends JpaRepository<TPrecios,Integer>{

}
