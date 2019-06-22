package com.ims.inventory.dtos.CustomDtos;

import com.ims.inventory.models.PrivilegeType;
import com.ims.inventory.models.User;

public class PrivilegeDto {

    private Long id;
    private User user;
    private PrivilegeType privilege;
    private Boolean hasAccess;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PrivilegeType getPrivilege() {
        return privilege;
    }

    public void setPrivilege(PrivilegeType privilege) {
        this.privilege = privilege;
    }

    public Boolean getHasAccess() {
        return hasAccess;
    }

    public void setHasAccess(Boolean hasAccess) {
        this.hasAccess = hasAccess;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
