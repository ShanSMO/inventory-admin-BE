package com.ims.inventory.repositories;

import com.ims.inventory.models.PurchaseOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderItemRepository extends JpaRepository<PurchaseOrderItem, Long>, QuerydslPredicateExecutor<PurchaseOrderItem> {
}
