package edu.uw.data.lecture6m2m.m2m_uni.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * uni directional many to many (no extra columns)
 * student has many courses
 * a course can have many students
 */

@Entity
@Table(name = "COURSE")
public class Course implements Serializable {
  private static final long serialVersionUID = 1L;


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="ID")
  private Integer id;

  @Column(name = "CODE")
  private String code;

  @Column(name = "TITLE")
  private String title;

  @Column(name = "DESCRIPTION")
  private String description;



  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "Course{" +
        "id=" + id +
        ", code='" + code + '\'' +
        ", title='" + title + '\'' +
        ", description='" + description + '\'' +
        '}';
  }
}