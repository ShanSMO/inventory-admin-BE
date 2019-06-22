package com.ims.inventory.repositories;

import com.ims.inventory.dtos.CustomDtos.SalesStaticDataSto;
import com.ims.inventory.models.Company;
import com.ims.inventory.models.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedQuery;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Repository
public interface SalesRepository extends JpaRepository<Sales,Long>, QuerydslPredicateExecutor<Sales> {

    @Query(value = "SELECT new com.ims.inventory.dtos.CustomDtos.SalesStaticDataSto(function('date_format',s.saleDate,'%y-%m-%d'),SUM(s.orderAmount)) FROM Sales s where s.company = :company group by function('date_format',s.saleDate,'%y-%m-%d')  order by s.id DESC ")
    List<SalesStaticDataSto> loadSalesStaticData(@Param("company") Company company);

    @Query(value = "SELECT new com.ims.inventory.dtos.CustomDtos.SalesStaticDataSto(" +
            "function('date_format',s.saleDate,'%y-%m-%d'),SUM(s.orderAmount)) " +
            "FROM Sales s group by " +
            "function('date_format',s.saleDate,'%y-%m-%d')")
    List<SalesStaticDataSto> loadSalesStaticDataByProduct();

    @Query("select count(s) from Sales s where s.company = :company")
    Long countByCompany(@Param("company") Company company);


}
