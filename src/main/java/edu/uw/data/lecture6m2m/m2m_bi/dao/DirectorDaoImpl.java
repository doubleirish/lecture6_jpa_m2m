package edu.uw.data.lecture6m2m.m2m_bi.dao;

import edu.uw.data.lecture6m2m.m2m_uni.model.Student;
import edu.uw.data.lecture6m2m.m2m_bi.model.Board;
import edu.uw.data.lecture6m2m.m2m_bi.model.Director;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


/**
 *  uni directional many to many with extra columns
  * a board has many directors
  * a director can sit on many boards
 */
@Transactional
@Repository("directorDao")
public class DirectorDaoImpl implements DirectorDao {

  static final Logger log = LoggerFactory.getLogger(DirectorDaoImpl.class);


  @PersistenceContext
  private EntityManager em;


  public Director findById(Integer id) {
     return em.find(Director.class, id);
   }

  public List<Director> findAllDirectors() {
    return em.createQuery("select d FROM Director d",Director.class).getResultList();
  }

  public List<Board> findAllBoards() {
    return em.createQuery("select b FROM Board b",Board.class).getResultList();
  }


  @Override
  //@Transactional(readOnly = true)
  public Director save(Director director) {
    if (director.getId() == null) { //insert
      em.persist(director);
    } else { //update
      em.merge(director);
    }
    // em.flush();
    log.info("saved " + director.getId());
    return director;
  }


  public void deleteUserById(Integer id) {
    Student user = em.find(Student.class, 1);
    em.remove(user);
  }


  public Board findBoardBySymbol(String symbol) {
    return (Board) em.createQuery(
            "SELECT b FROM Board b WHERE b.symbol LIKE :symbol")
            .setParameter("symbol", symbol)
            .getSingleResult();
  }
}
