package edu.uw.data.lecture6m2m.m2m_add_cols.model;

import com.google.common.base.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Set;

/**
 * Created by credmond on 4/13/15.
 */
@Entity
@Table(name="PROJECT", uniqueConstraints = {  @UniqueConstraint(columnNames = "PROJECT_NAME") })
public class Project {
  @Id
  @Column(name="ID",nullable = false ,unique = true)
  private Integer id;

  @Column(name="PROJECT_NAME",nullable = false, unique = true)
  private String projectName;


  @OneToMany(mappedBy="id.project", cascade = CascadeType.ALL)
  private Set<Team> teams;

  // Add an employee to the project.
  // Create an association object for the relationship and set its data.
  public void addEmployee(Employee employee, boolean teamLead) {

    Team team = new Team( );
    Team.Id teamId = new Team.Id(this,employee);
    team.setId(teamId);
    team.setProjectLead(teamLead);

    this.teams.add(team);
    // Also add the team object to the employee.
    //employee.getTeams().add(team); //TODO not needed  if cascade is enabled
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Set<Team> getTeams() {
    return teams;
  }

  public void setTeams(Set<Team> teams) {
    this.teams = teams;
  }

  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
            .add("id", id)
            .add("projectName", projectName)
            .toString();
  }
}
