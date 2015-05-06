package edu.uw.data.lecture6m2m;


import edu.uw.data.lecture6m2m.m2m_bi.dao.DirectorDao;
import edu.uw.data.lecture6m2m.m2m_bi.model.Board;
import edu.uw.data.lecture6m2m.m2m_bi.model.Director;
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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Embedded database is  always initialized cleasnly  as its stored in the target subdir which is cleared out on each run
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/app-spring.xml", "classpath:/datasource-embedded-init-p6spy.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)

public class DirectorDao_m2m_bi_Test extends AbstractTransactionalJUnit4SpringContextTests {

  static final Logger log = LoggerFactory.getLogger(DirectorDao_m2m_bi_Test.class);

  @Resource
  private DirectorDao directorDao;

  @Override
  @Resource(name = "dataSource")
  public void setDataSource(DataSource dataSource) {
    super.setDataSource(dataSource);
  }


  @Test
  public void addBoardToDirector() {

    int directorId = 3;
    Director beforeDirector = directorDao.findById(directorId);
    int countBoardsBefore = beforeDirector.getBoards().size();
    log.info("before update , director " + beforeDirector.getFirstName() + " has " + countBoardsBefore + " boards(s)");

    Board appl = directorDao.findBoardBySymbol("APPL");
    log.info("appl  " + appl);


    //enroll   Course to student
    beforeDirector.getBoards().add(appl);


    directorDao.save(beforeDirector);

    Director afterDirector = directorDao.findById(directorId);
    int countBoardsAfter = afterDirector.getBoards().size();
    log.info("after update director " + afterDirector.getFirstName() + " has " + countBoardsAfter + " boards(s)");

    assertThat(countBoardsAfter, is(countBoardsBefore + 1));

  }


  @Test
  public void findAllDirectors() {
    List<Director> directors = directorDao.findAllDirectors();
    assertNotNull(directors);
    assertTrue(directors.size() > 0);
    for (Director director : directors) {
      log.info("director :" + director);
      Set<Board> boards = director.getBoards();
      for (Board board : boards) {
        log.info("   board:" + board);
      }
    }
  }


  @Test
  public void findAllBoards() {
    List<Board> boards = directorDao.findAllBoards();
    assertNotNull(boards);
    assertTrue(boards.size() > 0);

    for (Board board : boards) {
      log.info("board:" + board);
      List<Director> directors = board.getDirectors();
      for (Director director : directors) {
        log.info("   director :" + director);
      }
    }
  }


}
