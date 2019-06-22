package com.ims.inventory.repositories;

import com.ims.inventory.models.SoftwareSystemSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SoftwareSystemSettingRepository extends JpaRepository<SoftwareSystemSettings,Long> ,
        QuerydslPredicateExecutor<SoftwareSystemSettings>{
}
