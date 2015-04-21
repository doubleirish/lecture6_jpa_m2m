package edu.uw.data.lecture6m2m.repository;


import edu.uw.data.lecture6m2m.embedded.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository("userPageRepository")
public interface UserPageRepository extends PagingAndSortingRepository<User, Integer>    {
// <Entity class , Primary Key aka Id class >)

}
