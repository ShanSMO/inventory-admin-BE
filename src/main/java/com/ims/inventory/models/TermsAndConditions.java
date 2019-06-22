package com.ims.inventory.models;

import javax.persistence.*;

@Entity
@Table(name = "TBL_TOS")
public class TermsAndConditions {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "company_join_id")
    private Company company;

    @Column(name = "tos")
    private String tos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getTos() {
        return tos;
    }

    public void setTos(String tos) {
        this.tos = tos;
    }
}
