package com.ims.inventory.repositories;

import com.ims.inventory.models.Company;
import com.ims.inventory.models.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<Vendor,Long>, QuerydslPredicateExecutor<Vendor> {

    @Query("select count(v) from Vendor v where v.company = :company")
    Long countByCompany(@Param("company") Company company);

}
