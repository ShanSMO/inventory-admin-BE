package com.ims.inventory.models;

import com.ims.inventory.dtos.SearchRequestDto;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "TBL_PURCHASE_ORDER")
public class PurchaseOrder extends SearchRequestDto{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "po_id")
    private Long id;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "purchase_order_id")
    private String purchaseOrderId;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "supplier_join_id")
    private Vendor vendor;

    @Column(name = "total_amount")
    private Double totalAmount;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<PurchaseOrderItem> purchaseOrderItems;

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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Set<PurchaseOrderItem> getPurchaseOrderItems() {
        return purchaseOrderItems;
    }

    public void setPurchaseOrderItems(Set<PurchaseOrderItem> purchaseOrderItems) {
        this.purchaseOrderItems = purchaseOrderItems;
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

    public String getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(String purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
