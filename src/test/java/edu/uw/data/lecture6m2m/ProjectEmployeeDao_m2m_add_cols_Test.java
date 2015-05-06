package edu.uw.data.lecture6m2m;


import edu.uw.data.lecture6m2m.m2m_add_cols.dao.ProjectEmployeeDao;
import edu.uw.data.lecture6m2m.m2m_add_cols.model.Employee;
import edu.uw.data.lecture6m2m.m2m_add_cols.model.Project;
import edu.uw.data.lecture6m2m.m2m_add_cols.model.Team;
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
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Embedded database is  always initialized cleasnly  as its stored in the target subdir which is cleared out on each run
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/app-spring.xml", "classpath:/datasource-embedded-init-p6spy.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)

public class ProjectEmployeeDao_m2m_add_cols_Test extends AbstractTransactionalJUnit4SpringContextTests {

  static final Logger log = LoggerFactory.getLogger(ProjectEmployeeDao_m2m_add_cols_Test.class);

  @Resource
  private ProjectEmployeeDao projectEmployeeDao;

  @Override
  @Resource(name = "dataSource")
  public void setDataSource(DataSource dataSource) {
    super.setDataSource(dataSource);
  }


  @Test
  public void addEmployeeToProject() {

    int employeeId = 1;
    Employee beforeEmployee = projectEmployeeDao.findEmployeeById(employeeId);
    int countTeamEmployeeIsOnBefore = beforeEmployee.getTeams().size();
    log.info("before update , employee " + beforeEmployee.getFirstName() + " is on " + countTeamEmployeeIsOnBefore + " teams(s)");

    Project chunnel = projectEmployeeDao.findProjectById(4);
    int chunnelTeamMembersBefore = chunnel.getTeams().size();
    log.info("before update , project " + chunnel.getProjectName() + " has " + chunnelTeamMembersBefore + " teams members");


    log.info("chunnel  " + chunnel);


    //enroll   Course to student
    boolean isLead = false;
    beforeEmployee.addProject(chunnel, isLead);

    //
    // cascade save
    //
    projectEmployeeDao.save(beforeEmployee);

//
    // employee has one more project
    //
    Employee afterEmployee = projectEmployeeDao.findEmployeeById(employeeId);
    int countTeamEmployeeIsOnAfter = afterEmployee.getTeams().size();
    log.info("after update , employee " + afterEmployee.getFirstName() + " is on " + countTeamEmployeeIsOnAfter + " teams(s)");
    assertThat(countTeamEmployeeIsOnAfter, is(countTeamEmployeeIsOnBefore + 1));


  }




  @Test
  public void listEmployeesAndTheirProjects() {
    List<Employee> allEmployees = projectEmployeeDao.findAllEmployees();
    assertNotNull(allEmployees);
    assertTrue(allEmployees.size() > 0);

    for (Employee employee : allEmployees) {
      log.info("employee :" + employee);
      Set<Team> teams = employee.getTeams();
      for (Team team : teams) {
        log.info("   project:" + team.getProject());
        log.info("      project lead:" + team.isProjectLead());
      }
    }
  }


  @Test
  public void listProjectsAndTheirTeamMembers() {
    List<Project> allProjects = projectEmployeeDao.findAllProjects();

    assertNotNull(allProjects);
    assertTrue(allProjects.size() > 0);

    for (Project project : allProjects) {
      log.info("project :" + project);
      Set<Team> employeesProjAssoc = project.getTeams();
      for (Team employeeProjAssoc : employeesProjAssoc) {
        log.info("   employee:" + employeeProjAssoc.getEmployee());
        log.info("      team lead:" + employeeProjAssoc.isProjectLead());
      }
    }
  }


}
