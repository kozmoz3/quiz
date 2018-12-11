package com.quizwish.quiz.repositorys

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

import com.quizwish.quiz.entity.Student
import java.util.List

@Repository("studentRepository")
public interface StudentRepository extends JpaRepository<Student, Serializable>{

	public abstract List<Student> findByTeacher(Integer teacher);
}
