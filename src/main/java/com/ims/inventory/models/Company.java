package com.ims.inventory.models;

import com.ims.inventory.dtos.ResourceFile;
import com.ims.inventory.enums.ActiveStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TBL_COMPANY")
public class Company {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "owner")
    private String owner;

    @Column(name = "contact")
    private Long contactNumber;

    @Column(name = "registered_date")
    private Date registeredDate;

    @Column(name = "expire_date")
    private Date expireDate;

    @Column(name = "company_logo")
    private String companyLogo;

    @Transient
    private ResourceFile resourceFile;

    @Column(name = "status")
    private ActiveStatus status = ActiveStatus.PENDING;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Long getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(Long contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public ActiveStatus getStatus() {
        return status;
    }

    public void setStatus(ActiveStatus status) {
        this.status = status;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public ResourceFile getResourceFile() {
        return resourceFile;
    }

    public void setResourceFile(ResourceFile resourceFile) {
        this.resourceFile = resourceFile;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }
}
