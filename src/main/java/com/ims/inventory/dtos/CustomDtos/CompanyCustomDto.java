package com.ims.inventory.dtos.CustomDtos;

import com.ims.inventory.dtos.ResourceFile;
import com.ims.inventory.enums.ActiveStatus;

public class CompanyCustomDto {

    private Long id;
    private String companyName;
    private String owner;
    private Long contactNumber;
    private String userName;
    private String userPassword;
    private String companyLogo;
    private ResourceFile resourceFile;
    private ActiveStatus status;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public ResourceFile getResourceFile() {
        return resourceFile;
    }

    public void setResourceFile(ResourceFile resourceFile) {
        this.resourceFile = resourceFile;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public ActiveStatus getStatus() {
        return status;
    }

    public void setStatus(ActiveStatus status) {
        this.status = status;
    }
}
