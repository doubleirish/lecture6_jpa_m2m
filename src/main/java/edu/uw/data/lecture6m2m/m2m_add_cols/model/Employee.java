package edu.uw.data.lecture6m2m.m2m_add_cols.model;

import com.google.common.base.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by credmond on 4/13/15.
 */
@Entity

public class Employee  implements Comparable{
  @Id
  @Column(name = "ID", unique = true, nullable = false)
  private Integer id;

  @Column(name="FIRST_NAME")
  private String firstName;

  @Column(name="LAST_NAME")
  private String lastName;

  @OneToMany(mappedBy="id.employee", cascade = CascadeType.ALL)
  private Set<Team> teams =new TreeSet<>();

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Set<Team> getTeams() {
    return teams;
  }

  public void addProject(Project project, boolean teamLead) {

    Team team = new Team( );
    Team.Id teamId = new Team.Id(project,this);

    team.setId(teamId);
    team.setProjectLead(teamLead);

    this.teams.add(team);
    // Also add the team object to the employee.
   // project.getTeams().add(team); //TODO not needed  if cascade is enabled

  }

  private void setTeams(Set<Team> teams) {
    this.teams = teams;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public int compareTo(Object o) {
    Employee that = (Employee) o;

    int  compare = this.getLastName().compareTo(that.getLastName());
    if (compare ==0) {
      compare = this.getFirstName().compareTo(that.getFirstName());
    }

    return compare;
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
            .add("id", id)
            .add("firstName", firstName)
            .add("lastName", lastName)
            .toString();
  }
}
