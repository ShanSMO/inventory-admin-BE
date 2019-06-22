package com.ims.inventory.repositories;

import com.ims.inventory.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, QuerydslPredicateExecutor<User> {

    @Query(value = "SELECT su FROM User su WHERE su.userName= :userName")
    public User findByUserName(@Param(value = "userName") String userName);

}
