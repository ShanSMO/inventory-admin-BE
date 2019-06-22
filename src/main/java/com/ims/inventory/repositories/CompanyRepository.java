package com.ims.inventory.repositories;

import com.ims.inventory.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long>, QuerydslPredicateExecutor<Company> {

}
