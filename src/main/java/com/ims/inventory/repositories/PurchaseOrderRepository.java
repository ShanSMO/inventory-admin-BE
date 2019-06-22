package com.ims.inventory.repositories;

import com.ims.inventory.models.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long>, QuerydslPredicateExecutor<PurchaseOrder> {
}
