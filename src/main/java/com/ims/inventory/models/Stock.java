package com.ims.inventory.models;

import com.ims.inventory.dtos.SearchRequestDto;
import com.ims.inventory.utils.StockStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TBL_STOCK")
public class Stock extends SearchRequestDto{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private Long id;

    @Column(name = "barcode")
    private String barcode;

    @OneToOne
    @JoinColumn(name = "product_join_id")
    private Product product;

    @Column(name = "count")
    private Integer count;

    @Column(name = "status")
    private StockStatus status;

    @Column(name = "added_date")
    private Date addedDate;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "company_join_id")
    private Company company;

    @Transient
    private Category category;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public StockStatus getStatus() {
        return status;
    }

    public void setStatus(StockStatus status) {
        this.status = status;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
