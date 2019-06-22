package com.ims.inventory.models;

import com.ims.inventory.enums.ActiveStatus;

import javax.persistence.*;

@Entity
@Table(name = "TBL_BRAND")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "status")
    private ActiveStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public ActiveStatus getStatus() {
        return status;
    }

    public void setStatus(ActiveStatus status) {
        this.status = status;
    }
}
