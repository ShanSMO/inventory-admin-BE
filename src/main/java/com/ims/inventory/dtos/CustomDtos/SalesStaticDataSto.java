package com.ims.inventory.dtos.CustomDtos;


public class SalesStaticDataSto {

    private String saleDate;
    private Double totalAmount;

    public SalesStaticDataSto(String saleDate, Double totalAmount) {
        this.saleDate = saleDate;
        this.totalAmount = totalAmount;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
