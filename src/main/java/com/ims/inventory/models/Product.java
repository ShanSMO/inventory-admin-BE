package com.ims.inventory.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ims.inventory.dtos.ResourceFile;
import com.ims.inventory.dtos.SearchRequestDto;
import com.ims.inventory.enums.ActiveStatus;

import javax.persistence.*;

@Entity
@Table(name = "TBL_PRODUCT")
public class Product extends SearchRequestDto{

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_code")
    private String productCode;

    @Column(name = "product_barcode")
    private String barcode;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_model")
    private String productModel;

    @Column(name = "product_image_url")
    private String productImageUrl;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_join_id")
    private Category category;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "sub_category_join_id")
    private SubCategory subCategory;

    @Column(name = "product_description")
    private String description;

    @Column(name = "status")
    private ActiveStatus status;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "company_join_id")
    private Company company;

    @OneToOne
    @JoinColumn(name = "brand_join_id")
    private Brand brand;

    @Transient
    private ResourceFile resourceFile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ResourceFile getResourceFile() {
        return resourceFile;
    }

    public void setResourceFile(ResourceFile resourceFile) {
        this.resourceFile = resourceFile;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public ActiveStatus getStatus() {
        return status;
    }

    public void setStatus(ActiveStatus status) {
        this.status = status;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }
}
