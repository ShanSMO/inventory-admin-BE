package com.ims.inventory.models;

import com.ims.inventory.dtos.SearchRequestDto;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TBL_ADDRESS")
public class Address extends SearchRequestDto implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "house_number")
    private String houseNo;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "zip_code")
    private Integer zipCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }
}
