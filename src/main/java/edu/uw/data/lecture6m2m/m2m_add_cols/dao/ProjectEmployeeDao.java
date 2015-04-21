package edu.uw.data.lecture6m2m.m2m_add_cols.dao;

import edu.uw.data.lecture6m2m.m2m_add_cols.model.Employee;
import edu.uw.data.lecture6m2m.m2m_add_cols.model.Project;

import java.util.List;


/**
 * uni directional many to many (no extra columns)
 * student has many courses
 * a course can have many students
 */
public interface ProjectEmployeeDao {

  // CRUD :  IInsert  and update
  Project save(Project prj);
  Employee save(Employee emp);


  // Queries
  Project findProjectById(Integer id);
  // Queries
  Employee findEmployeeById(Integer id);


  List<Project> findAllProjects();
  List<Employee> findAllEmployees();


}
