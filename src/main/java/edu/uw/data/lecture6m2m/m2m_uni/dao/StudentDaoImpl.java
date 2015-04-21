package edu.uw.data.lecture6m2m.m2m_uni.dao;

import edu.uw.data.lecture6m2m.m2m_uni.model.Course;
import edu.uw.data.lecture6m2m.m2m_uni.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * uni directional many to many (no extra columns)
 * student has many courses
 * a course can have many students
 */
@Transactional
@Repository("studentDao")
public class StudentDaoImpl implements StudentDao {

  static final Logger log = LoggerFactory.getLogger(StudentDaoImpl.class);


  @PersistenceContext
  private EntityManager em;


  public Student findStudentById(Integer id) {
    return em.find(Student.class, id);
  }

  //@Transactional(readOnly = true)
  public List<Student> findAllStudents() {
    return em.createQuery("select s FROM Student s", Student.class).getResultList();
  }


  @Override
  public Student findByUsername(String uname) {
    return (Student) em.createQuery(
        "SELECT s FROM Student s WHERE s.userName LIKE :uname")
        .setParameter("uname", uname)
        .getSingleResult();
  }

  @Override
  //@Transactional(readOnly = true)
  public Student save(Student student) {
    if (student.getId() == null) { //insert
      em.persist(student);
    } else { //update
      em.merge(student);
    }
    // em.flush();
    log.info("saved " + student.getId());
    return student;
  }




  public Course findCourseByCode(String code) {
    return (Course) em.createQuery(
            "SELECT c FROM Course c WHERE c.code LIKE :code")
            .setParameter("code", code)
            .getSingleResult();
  }
}
