package com.ims.inventory.models;

import com.ims.inventory.enums.ActiveStatus;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tbl_user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_name")
    private String roleName;

//    @OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
//    @JoinColumn(name = "privilege_join_id")
//    private Set<PrivilegeType> privilegeTypes;

    @Column(name = "status")
    private ActiveStatus status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

//    public Set<PrivilegeType> getPrivilegeTypes() {
//        return privilegeTypes;
//    }

//    public void setPrivilegeTypes(Set<PrivilegeType> privilegeTypes) {
//        this.privilegeTypes = privilegeTypes;
//    }

    public ActiveStatus getStatus() {
        return status;
    }

    public void setStatus(ActiveStatus status) {
        this.status = status;
    }
}
