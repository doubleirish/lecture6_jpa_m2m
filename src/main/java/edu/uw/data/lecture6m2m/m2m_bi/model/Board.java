package edu.uw.data.lecture6m2m.m2m_bi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * uni directional many to many with extra columns
 * a board has many directors
 * a director can sit on many boards
 */

@Entity
@Table(name = "BOARD")
public class Board implements Serializable {
  private static final long serialVersionUID = 1L;


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="ID")
  private Integer id;

  @Column(name = "SYMBOL")
   private String symbol;



  @Column(name = "COMPANY_NAME")
  private String companyName;

  @ManyToMany(mappedBy="boards")
    private List<Director> directors;



  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public List<Director> getDirectors() {
    return directors;
  }

  public void setDirectors(List<Director> directors) {
    this.directors = directors;
  }

  @Override
  public String toString() {
    return "Board{" +
        "companyName='" + companyName + '\'' +
        ", symbol='" + symbol + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Board)) return false;

    Board board = (Board) o;

    if (!symbol.equals(board.symbol)) return false;

    return true;
  }

  @Override
  public int hashCode() {
    return symbol.hashCode();
  }
}