package com.ims.inventory.repositories;

import com.ims.inventory.models.Company;
import com.ims.inventory.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product> {

    @Query("SELECT product FROM Product product GROUP BY product.category")
    List<Product> loadProductGroupByCategory();


    @Query("select count(p) from Product p where p.company = :company")
    Long countByCompany(@Param("company") Company company);


}
