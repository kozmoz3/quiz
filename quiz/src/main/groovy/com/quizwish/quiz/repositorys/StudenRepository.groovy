package com.quizwish.quiz.repositorys

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

import com.quizwish.quiz.entity.Student
import java.util.List

@Repository("studentRepository")
public interface StudentRepository extends JpaRepository<Student, String>{

    List<Student> findByTeacher(Integer teacher);
	
	@Query(value = "SELECT * FROM student std WHERE std.teacher = ?1 and std.nullable = 1", nativeQuery = true)
	List<Student> findAllStudent(Integer teacher);
	
	
}
