package com.ims.inventory.dtos.CustomDtos;

import com.ims.inventory.models.Category;
import com.ims.inventory.models.Company;
import com.ims.inventory.models.Product;

import java.util.Date;

public class ChartDataRequestDto {

    private Long companyId;
    private String requestType;
    private Date startDate;
    private Date endDate;
    private Category category;
    private Product product;


    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
