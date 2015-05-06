package edu.uw.data.lecture6m2m.repository.custom;

import edu.uw.data.lecture6m2m.embedded.model.User;

import java.util.List;

/**
 * see also http://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.single-repository-behaviour
 */
public interface UserRepositoryCustom {
    public List<User> findLocalUsers(String city);
}
