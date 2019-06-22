package com.ims.inventory.dtos.CustomDtos;

public class StatisticDataDto {

    private Long customerCount;
    private Long supplierCount;
    private Long productCount;
    private Long categoryCount;
    private Long salesCount;

    public Long getCustomerCount() {
        return customerCount;
    }

    public void setCustomerCount(Long customerCount) {
        this.customerCount = customerCount;
    }

    public Long getSupplierCount() {
        return supplierCount;
    }

    public void setSupplierCount(Long supplierCount) {
        this.supplierCount = supplierCount;
    }

    public Long getProductCount() {
        return productCount;
    }

    public void setProductCount(Long productCount) {
        this.productCount = productCount;
    }

    public Long getCategoryCount() {
        return categoryCount;
    }

    public void setCategoryCount(Long categoryCount) {
        this.categoryCount = categoryCount;
    }

    public Long getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(Long salesCount) {
        this.salesCount = salesCount;
    }
}
