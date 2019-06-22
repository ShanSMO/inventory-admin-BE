package com.ims.inventory.dtos.CustomDtos;

import com.ims.inventory.models.Company;

import java.util.Date;

public class ManagementRequestDto {

    private Date fromDate;
    private Date toDate;
    private Company company;

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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
