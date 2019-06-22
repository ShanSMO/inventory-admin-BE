package com.ims.inventory.repositories;

import com.ims.inventory.models.RecentActivity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecentActivityRepository extends JpaRepository<RecentActivity, Long>, QuerydslPredicateExecutor<RecentActivity> {

    @Query("SELECT ra from RecentActivity ra WHERE ra.company.id = :companyId order by ra.id DESC")
    List<RecentActivity> findRecentTasks(@Param(value = "companyId") Long companyId, Pageable pageable);

}
