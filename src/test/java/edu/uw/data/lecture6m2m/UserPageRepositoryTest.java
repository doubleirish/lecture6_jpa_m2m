package edu.uw.data.lecture6m2m;


import edu.uw.data.lecture6m2m.embedded.model.User;
import edu.uw.data.lecture6m2m.repository.UserPageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

public class UserPageRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

  static final Logger log = LoggerFactory.getLogger(UserPageRepositoryTest.class);

  @Resource
  private UserPageRepository userPageRepository;

  @Override
  @Resource(name = "dataSource")
  public void setDataSource(DataSource dataSource) {
    super.setDataSource(dataSource);
  }




  @Test
  public void pageAllUsersByFirstName() {
    final int PAGE_SIZE = 5;
    int totalResults = (int) userPageRepository.count();

    int totalPages = (int) Math.ceil(totalResults / PAGE_SIZE);

    for (int pageNumber = 1; pageNumber <= totalPages; pageNumber++) {


      PageRequest pageRequest =
              new PageRequest(pageNumber - 1,
                      PAGE_SIZE,
                      Sort.Direction.ASC,
                      "firstName");
      Iterable<User> users = userPageRepository.findAll(pageRequest);
      System.out.println("Page " + pageNumber);
      ;
      users.forEach( user -> System.out.println(user.getFirstName()));

    }
  }

    @Test
    public void pageAllUsersBillingStreet() {
      final int PAGE_SIZE=5;
      int totalResults = (int)userPageRepository.count();

      int totalPages = (int) Math.ceil(totalResults / PAGE_SIZE);

      for (int pageNumber= 1;pageNumber <=totalPages; pageNumber++) {


        PageRequest pageRequest =
                new PageRequest(pageNumber - 1,
                        PAGE_SIZE,
                        Sort.Direction.ASC,
                        "billingAddr.street");
        Iterable<User> users = userPageRepository.findAll(pageRequest);
        System.out.println("Page "+pageNumber);;
        users.forEach( user -> System.out.println(user.getBillingAddr().getStreet()));

      }


  }

  @Test
  public void findOne() {
    User credmond = userPageRepository.findOne(1);
    System.out.println("findOne "+credmond);
    assertNotNull(credmond);
  }



}
