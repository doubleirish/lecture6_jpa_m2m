package edu.uw.data.lecture6m2m.repository;


import edu.uw.data.lecture6m2m.embedded.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface UserCrudRepository extends CrudRepository<User, Integer> {  // <Entity class , Primary Key aka Id class >)

    void delete(User deleted);

    User save(User persisted);

    User  findOne(Integer id);

    List<User> findAll();

    List<User> findByLastNameOrderByUsername(   String lastname);

    List<User> findByBillingAddrStateOrderByBillingAddrStreet(String usState);

    //TODO Query Lab 1 uncomment line below and run corresponding test in UserQueryRepositoryTest
    List<User> findByBillingAddrStateOrderByCreditCardExpirationDate(String state);

    @Query("SELECT u FROM User u WHERE u.creditCard.expirationDate < CURRENT_DATE ")
    List<User> findExpired();

    //TODO Query Lab 2 uncomment line below and add a @Query to find users with unexpired credit cards
    //List<User> findUsersWithUnExpiredCreditCard();



}
