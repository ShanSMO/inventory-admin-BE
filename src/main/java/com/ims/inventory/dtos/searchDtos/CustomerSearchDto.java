package com.ims.inventory.dtos.searchDtos;

import com.ims.inventory.dtos.SearchRequestDto;
import com.ims.inventory.models.Address;

public class CustomerSearchDto extends SearchRequestDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String website;
    private Integer phoneNumber;
    private Integer faxNumber;
    private Address address;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(Integer faxNumber) {
        this.faxNumber = faxNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
