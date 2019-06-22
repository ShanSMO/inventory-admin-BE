package com.ims.inventory.repositories;

import com.ims.inventory.models.TermsAndConditions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TosRepository extends JpaRepository<TermsAndConditions,Long>, QuerydslPredicateExecutor<TermsAndConditions> {
}
