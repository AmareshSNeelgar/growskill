package com.platform.growskill.repo;

import com.platform.growskill.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
@Repository
@Transactional
public interface UserRepo extends CrudRepository<User, Long> {

    @Query(value="select u from User u where u.email = ?1 ")
     Optional<User> getByEmail(String email);

}
