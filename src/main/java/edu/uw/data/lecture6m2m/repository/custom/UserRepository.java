package edu.uw.data.lecture6m2m.repository.custom;

import edu.uw.data.lecture6m2m.embedded.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by credmond on 5/6/15.
 */
public interface UserRepository
    extends JpaRepository<User, Long>, UserRepositoryCustom {

}
