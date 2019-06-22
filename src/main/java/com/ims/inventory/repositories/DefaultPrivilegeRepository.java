package com.ims.inventory.repositories;

import com.ims.inventory.models.DefaultPrivileges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DefaultPrivilegeRepository extends JpaRepository<DefaultPrivileges,Long>,
        QuerydslPredicateExecutor<DefaultPrivileges> {
}
