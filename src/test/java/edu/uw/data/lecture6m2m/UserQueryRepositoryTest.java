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
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Embedded database is  always initialized cleasnly  as its stored in the target subdir which is cleared out on each run
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/app-spring.xml", "classpath:/datasource-embedded-init.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)

public class UserQueryRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

  static final Logger log = LoggerFactory.getLogger(UserQueryRepositoryTest.class);

  @Resource
    private UserCrudRepository userCrudRepository;

  @Override
  @Resource(name = "dataSource")
  public void setDataSource(DataSource dataSource) {
    super.setDataSource(dataSource);
  }



 // Query LAB 1 HQL from Method name
  @Test
  public void findByBillingAddrStateOrderByCreditcardExpirationDateTest_LAB() {
    List<User> users = userCrudRepository.findByBillingAddrStateOrderByCreditCardExpirationDate("WA");


    assertNotNull(users);
    for (User user : users) {
      System.out.println( user.getBillingAddr().getState() +" "+user.getCreditCard().getExpirationDate());
    }
    assertThat(users.size(),is(5));
  }


  // Query LAB 2 @Query
  //  @Test
//  public void findUserWithUnExpiredCreditCardTest() {
//    List<User> users = userCrudRepository.findUsersWithUnExpiredCreditCard(); // TODO Lab 2 add @Query to UserCrudRepository.findUsersWithUnExpiredCreditCard()
//
//    assertNotNull(users);
//    for (User user : users) {
//      System.out.println( user.getBillingAddr().getState() +" "+user.getCreditCard().getExpirationDate());
//    }
//    assertThat(users.size(),is(4));
//  }


  @Test
  public void findByFirstNameAndLastNameOrderByUsername() {
      List<User> users = userCrudRepository.findByLastNameOrderByUsername("Redmond");

    assertNotNull(users);
    for (User user : users) {
      System.out.println( user.getUsername() +" "+ user.getFirstName()+" "+ user.getLastName());
    }
  }

  @Test
  public void findByBillingAddrStateOrderByBillingAddrStreet() {
    List<User> users = userCrudRepository.findByBillingAddrStateOrderByBillingAddrStreet("WA");

    assertNotNull(users);
    for (User user : users) {
      System.out.println( user.getBillingAddr().getStreet() +" "+user.getBillingAddr().getState());
    }
  }




  @Test
  public void findExpired() {
    List<User> users = userCrudRepository.findExpired();

    assertNotNull(users);
    for (User user : users) {
      System.out.println( user.getUsername() +" "+user.getCreditCard().getExpirationDate());
    }
  }


}
