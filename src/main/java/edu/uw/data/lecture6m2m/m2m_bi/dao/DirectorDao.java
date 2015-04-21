package edu.uw.data.lecture6m2m.m2m_bi.dao;

import edu.uw.data.lecture6m2m.m2m_bi.model.Board;
import edu.uw.data.lecture6m2m.m2m_bi.model.Director;

import java.util.List;

/**
 * uni directional many to many with extra columns
 * a board has many directors
 * a director can sit on many boards
 */
public interface DirectorDao {


  Director save(Director director);


  Director findById(Integer id);

  List<Director> findAllDirectors();

  List<Board> findAllBoards();


  Board findBoardBySymbol(String symbol);

}
