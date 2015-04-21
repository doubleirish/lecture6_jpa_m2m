package edu.uw.data.lecture6m2m.m2m_bi.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

/**
 * uni directional many to many with extra columns
 * a board has many directors
 * a director can sit on many boards
 */
@Entity
@Table(name = "Director")
public class Director implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private Integer id;


  @Column(name = "FIRSTNAME")
  private String firstName;

  @Column(name = "LASTNAME")
  private String lastName;

  @Temporal(TemporalType.DATE)
  @Column(name = "DOB")
  private Date dob;

  //STUDENT TABLE
  // ID  FIRSTNAME ...
  // ==  ============
  // 1   Conor ...

  //COURSE TABLE
  // ID  CODE ...
  // ==  ========
  // 1   CP225 ...


  //STUDENT_COURSE TABLE
  // STUDENT_ID  COURSE_ID
  //    1           1

  // from the point of view of the join table , requires a FK to the source(this class i.e student) primary key
  @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
        name="DIRECTOR_BOARD",
        joinColumns={@JoinColumn(name="DIRECTOR_ID", referencedColumnName="ID")},
        inverseJoinColumns={@JoinColumn(name="BOARD_ID", referencedColumnName="ID")})
  private Set<Board> boards = new TreeSet<>();

  public Set<Board> getBoards() {
    return boards;
  }

  public void setBoards(Set<Board> boards) {
    this.boards = boards;
  }

  public void addBoard(Board board) {
     boards.add(board);
   }


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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

  public Date getDob() {
    return dob;
  }

  public void setDob(Date dob) {
    this.dob = dob;
  }

  @Override
  public String toString() {
    return "Director{" +
        "id=" + id +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", dob=" + dob +

        '}';
  }

  public static class Builder {
      private Director student;

      public Builder() {
        student = new Director();
      }

      public Builder builder() {
        return new Builder();
      }

      public Builder id(Integer id) {
        student.setId(id);
        return this;
      }



      public Builder firstName(String firstName) {
        student.setFirstName(firstName);
        return this;
      }

      public Builder lastName(String lastName) {
        student.setLastName(lastName);
        return this;
      }

      public Builder dob(Date dob) {
        student.setDob(dob);
        return this;
      }



      public Director build() {
        //validate ??;
        return student;
      }
    }

}
