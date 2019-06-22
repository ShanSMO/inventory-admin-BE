package com.ims.inventory.models;

import com.ims.inventory.dtos.SearchRequestDto;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "TBL_SALES")
public class Sales extends SearchRequestDto{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sales_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_join_id")
    private Customer customer;

    @Column(name = "sale_date")
    private Date saleDate;

    @Column(name = "order_amount")
    private Double orderAmount;

    @Column(name = "order_status")
    private String status;

    @Column(name = "order_id")
    private String orderId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_item_join_id")
    private Set<SalesOrderItem> salesOrderItems;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "company_join_id")
    private Company company;

    @Transient
    private Date fromDate;

    @Transient
    private Date toDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<SalesOrderItem> getSalesOrderItems() {
        return salesOrderItems;
    }

    public void setSalesOrderItems(Set<SalesOrderItem> salesOrderItems) {
        this.salesOrderItems = salesOrderItems;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
