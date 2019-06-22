package com.ims.inventory.repositories;

import com.ims.inventory.models.Company;
import com.ims.inventory.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, QuerydslPredicateExecutor<Customer>{

    @Query("select count(c) from Customer c where c.company = :company")
    Long countByCompany(@Param("company") Company company);

}
