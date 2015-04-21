package edu.uw.data.lecture6m2m.m2m_uni.dao;

import edu.uw.data.lecture6m2m.m2m_uni.model.Course;
import edu.uw.data.lecture6m2m.m2m_uni.model.Student;

import java.util.List;


/**
 * uni directional many to many (no extra columns)
 * student has many courses
 * a course can have many students
 */
public interface StudentDao {

  // CRUD :  IInsert  and update
  Student save(Student user);


  // Queries
  Student findStudentById(Integer id);

  Student findByUsername(String username);

  List<Student> findAllStudents();


  Course findCourseByCode(String code);

}
