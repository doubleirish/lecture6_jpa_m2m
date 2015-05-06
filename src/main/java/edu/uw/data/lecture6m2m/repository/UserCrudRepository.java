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

    List<User> findByBillingAddrStateOrderByCreditCardExpirationDate(String state);

    @Query("SELECT u FROM User u WHERE u.creditCard.expirationDate < CURRENT_DATE ")
    public List<User> findExpired();



}
