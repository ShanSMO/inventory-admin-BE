package com.ims.inventory.repositories;

import com.ims.inventory.models.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long>, QuerydslPredicateExecutor<SubCategory>{
}
