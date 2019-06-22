package com.ims.inventory.repositories;

import com.ims.inventory.models.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long>, QuerydslPredicateExecutor<Stock> {
}
