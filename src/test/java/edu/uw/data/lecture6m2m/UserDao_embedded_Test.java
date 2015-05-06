package edu.uw.data.lecture6m2m;


import edu.uw.data.lecture6m2m.embedded.dao.UserDao;
import edu.uw.data.lecture6m2m.embedded.model.User;
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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Embedded database is  always initialized cleasnly  as its stored in the target subdir which is cleared out on each run
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/app-spring.xml", "classpath:/datasource-embedded-init.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)

public class UserDao_embedded_Test extends AbstractTransactionalJUnit4SpringContextTests {

  static final Logger log = LoggerFactory.getLogger(UserDao_embedded_Test.class);

  @Resource
  private UserDao userDao;

  @Override
  @Resource(name = "dataSource")
  public void setDataSource(DataSource dataSource) {
    super.setDataSource(dataSource);
  }




  @Test
  public void verifyCreditCardWasEmbeddedCorrectlyTest(){
    List<User> users = userDao.findAll();
    assertNotNull(users);

    assertTrue(users.size() > 0);

    for (User user : users) {
      System.out.println("user with embedded credit card"+user);
    }
    assertNotNull(users.get(0).getCreditCard());

  }

  @Test
  public void verifyProfileWasEmbeddedCorrectlyTest_LAB() {
    List<User> users = userDao.findAll();
    assertNotNull(users);

    assertTrue(users.size() > 0);

    for (User user : users) {
      System.out.println("user with embedded profile "+user);
    }
    assertNotNull(users.get(0).getProfile());

  }



}
