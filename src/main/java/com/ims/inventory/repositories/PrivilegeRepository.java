package com.ims.inventory.repositories;

import com.ims.inventory.models.PrivilegeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<PrivilegeType, Long>,
        QuerydslPredicateExecutor<PrivilegeType> {

}
