package edu.uw.data.lecture6m2m.m2m_add_cols.dao;

import edu.uw.data.lecture6m2m.m2m_add_cols.model.Employee;
import edu.uw.data.lecture6m2m.m2m_add_cols.model.Project;
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
@Repository("projectEmployeeDao")
public class ProjectEmployeeDaoImpl implements ProjectEmployeeDao {

  static final Logger log = LoggerFactory.getLogger(ProjectEmployeeDaoImpl.class);


  @PersistenceContext
  private EntityManager em;


  public Project findProjectById(Integer id) {
    return em.find(Project.class, id);
  }
  public Employee findEmployeeById(Integer id) {
    return em.find(Employee.class, id);
  }


  //@Transactional(readOnly = true)
  public List<Project> findAllProjects() {
    return em.createQuery("FROM Project", Project.class).getResultList();
  }

  public List<Employee> findAllEmployees() {
    return em.createQuery("FROM Employee", Employee.class).getResultList();
  }



  @Override
  //@Transactional(readOnly = true)
  public Project save(Project project) {
    if (project.getId() == null) { //insert
      em.persist(project);
    } else { //update
      em.merge(project);
    }
    // em.flush();
    log.info("saved " + project.getId());
    return project;
  }


  @Override
  //@Transactional(readOnly = true)
  public Employee save(Employee employee) {
    if (employee.getId() == null) { //insert
      em.persist(employee);
    } else { //update
      em.merge(employee);
    }
    // em.flush();
    log.info("saved " + employee.getId());
    return employee;
  }


}
