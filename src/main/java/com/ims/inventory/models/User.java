package com.ims.inventory.models;

import com.ims.inventory.dtos.SearchRequestDto;
import com.ims.inventory.enums.ActiveStatus;
//import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TBL_USER")
public class User extends SearchRequestDto{

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "status")
    private ActiveStatus status;


    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "J_TBL_USER_PRIVILEGE",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id"))
    private List<PrivilegeType> privilegeTypes;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "company_join_id")
    private Company company;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ActiveStatus getStatus() {
        return status;
    }

    public void setStatus(ActiveStatus status) {
        this.status = status;
    }

    public List<PrivilegeType> getPrivilegeTypes() {
        return privilegeTypes;
    }

    public void setPrivilegeTypes(List<PrivilegeType> privilegeTypes) {
        this.privilegeTypes = privilegeTypes;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
