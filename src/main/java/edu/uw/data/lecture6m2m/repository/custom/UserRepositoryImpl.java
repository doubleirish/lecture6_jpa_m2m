package edu.uw.data.lecture6m2m.repository.custom;

import edu.uw.data.lecture6m2m.embedded.model.User;
import edu.uw.data.lecture6m2m.m2m_uni.dao.StudentDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by credmond on 5/6/15.
 */
public class UserRepositoryImpl implements UserRepositoryCustom{

  static final Logger log = LoggerFactory.getLogger(StudentDaoImpl.class);


    @PersistenceContext
    private EntityManager em;

  @Override
  public List<User> findLocalUsers(String city) {
    return   em.createQuery("select u FROM User u where  u.shippingAddr.city = :city")
        .setParameter("city",city)
        .getResultList();
  }
}
