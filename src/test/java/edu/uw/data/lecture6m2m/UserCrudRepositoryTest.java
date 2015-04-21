package edu.uw.data.lecture6m2m;


import edu.uw.data.lecture6m2m.embedded.model.User;
import edu.uw.data.lecture6m2m.repository.UserCrudRepository;
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

import static org.junit.Assert.assertNotNull;

/**
 * Embedded database is  always initialized cleasnly  as its stored in the target subdir which is cleared out on each run
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/app-spring.xml", "classpath:/datasource-embedded-init.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)

public class UserCrudRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

  static final Logger log = LoggerFactory.getLogger(UserCrudRepositoryTest.class);

  @Resource
  private UserCrudRepository userCrudRepository;

  @Override
  @Resource(name = "dataSource")
  public void setDataSource(DataSource dataSource) {
    super.setDataSource(dataSource);
  }




  @Test
  public void findAllUsers() {
    Iterable<User> users = userCrudRepository.findAll();
    assertNotNull(users);
    users.forEach(System.out::println);
  }

  @Test
  public void findOne() {
    User credmond = userCrudRepository.findOne(1);
    System.out.println("findOne "+credmond);
    assertNotNull(credmond);
  }



}
