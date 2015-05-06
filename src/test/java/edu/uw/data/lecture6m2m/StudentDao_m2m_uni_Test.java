package edu.uw.data.lecture6m2m;


import edu.uw.data.lecture6m2m.m2m_uni.dao.StudentDao;
import edu.uw.data.lecture6m2m.m2m_uni.model.Course;
import edu.uw.data.lecture6m2m.m2m_uni.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 *  Embedded database is  always initialized cleasnly  as its stored in the target subdir which is cleared out on each run
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/app-spring.xml",  "classpath:/datasource-embedded-init-p6spy.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)

public class StudentDao_m2m_uni_Test extends AbstractTransactionalJUnit4SpringContextTests {

  static final Logger log = LoggerFactory.getLogger(StudentDao_m2m_uni_Test.class);

  @Resource
private StudentDao studentDao;

  @Override
  @Resource(name = "dataSource")
  public void setDataSource(DataSource dataSource) {
    super.setDataSource(dataSource);
  }




  @Test
  public void findByUsername()    {

    Student credmond = studentDao.findByUsername("credmond");
    log.info("found  "+credmond);
    assertNotNull(credmond);
    assertEquals(1L, credmond.getId().longValue());
  }



  @Test
  public void findById()    {
    Student student = studentDao.findStudentById(1);
    log.info("foundUser "+student);
    assertNotNull(student);
    assertNotNull(student.getId());
    assertThat(student.getUserName(), is("credmond"));
  }






  @Test
  public void addCourseToStudent()    {

    int studentId = 3;
    Student beforeStudent  = studentDao.findStudentById(studentId);
    int countCoursesBefore = beforeStudent.getCourses().size();
    log.info("before update , student "+beforeStudent.getUserName()+" has "+ countCoursesBefore + " courses(s)");

    Course cp225 = studentDao.findCourseByCode("CP225");
    log.info("cp225  "+cp225);


    //
    // enroll student in a couurse TODO requires cascade
    //
    beforeStudent.getCourses().add(cp225);


    studentDao.save(beforeStudent);

    Student afterStudent  = studentDao.findStudentById(studentId);
    int countCoursesAfter = afterStudent.getCourses().size();
    log.info("after update student "+afterStudent.getUserName()+" has "+ countCoursesAfter + " courses(s)");

    assertThat(countCoursesAfter,is(countCoursesBefore+1));

  }





  @Test
  public void findAll()    {
    List<Student> students = studentDao.findAllStudents();
    assertNotNull(students);
    assertTrue(students.size() >0);
    students.forEach(System.out::println);
  }


}
