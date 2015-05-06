package edu.uw.data.lecture6m2m.m2m_uni.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

/**
 * uni directional many to many (no extra columns)
 * student has many courses
 * a course can have many students
 */
@Entity
@Table(name = "STUDENT")
public class Student implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private Integer id;

  @Column(name = "USERNAME")
  private String userName;

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
        name="STUDENT_COURSE",
        joinColumns={@JoinColumn(name="STUDENT_ID", referencedColumnName="ID")},
        inverseJoinColumns={@JoinColumn(name="COURSE_ID", referencedColumnName="ID")})
  private Set<Course> courses = new TreeSet<>();

  public Set<Course> getCourses() {
    return courses;
  }

  public void setCourses(Set<Course> courses) {
    this.courses = courses;
  }

  public void addCourse(Course course) {
     courses.add(course);
   }


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
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
    return "Student{" +
        "userName='" + userName + '\'' +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", dob=" + dob +
        ", courses=" + courses +
        ", id=" + id +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Student)) return false;

    Student student = (Student) o;

    if (!userName.equals(student.userName)) return false;

    return true;
  }

  @Override
  public int hashCode() {
    return userName.hashCode();
  }

  public static class Builder {
      private Student student;

      public Builder() {
        student = new Student();
      }

      public Builder builder() {
        return new Builder();
      }

      public Builder id(Integer id) {
        student.setId(id);
        return this;
      }

      public Builder userName(String userName) {
        student.setUserName(userName);
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



      public Student build() {
        //validate ??;
        return student;
      }
    }

}
