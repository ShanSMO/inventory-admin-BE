package com.ims.inventory.models;

import com.ims.inventory.enums.ActiveStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "tbl_super_admin")
public class SuperAdmin{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "status")
    private ActiveStatus status;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public ActiveStatus getStatus() {
        return status;
    }

    public void setStatus(ActiveStatus status) {
        this.status = status;
    }
}
