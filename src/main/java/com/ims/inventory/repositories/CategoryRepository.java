package com.ims.inventory.repositories;

import com.ims.inventory.models.Category;
import com.ims.inventory.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository  extends JpaRepository<Category, Long>, QuerydslPredicateExecutor<Category> {

    @Query("select count(c) from Category c where c.company = :company")
    Long countByCompany(@Param("company") Company company);

}
