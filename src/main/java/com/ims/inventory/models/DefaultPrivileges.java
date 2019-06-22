package com.ims.inventory.models;

import com.ims.inventory.enums.ActiveStatus;
import com.ims.inventory.enums.PriviledgeType;

import javax.persistence.*;

@Entity
@Table(name = "TBL_DEFAULT_PRIVILEGES")
public class DefaultPrivileges {

    @Id
    @Column(name = "priviledge_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "priviledge_name")
    private String privilegeName;

    @Column(name = "privilege_type")
    private PriviledgeType privilegeType;

    @Column(name = "parent_privilege")
    private Long parentId;

    @Column(name = "url")
    private String url;

    @Column(name = "icon")
    private String icon;

    @Column(name = "level")
    private String level;

    @Column(name = "level_parent")
    private String levelParent;

    @Column(name = "status")
    private ActiveStatus status = ActiveStatus.ACTIVE;

    @Column(name = "prv_id")
    private Long prvId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrivilegeName() {
        return privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    public PriviledgeType getPrivilegeType() {
        return privilegeType;
    }

    public void setPrivilegeType(PriviledgeType privilegeType) {
        this.privilegeType = privilegeType;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevelParent() {
        return levelParent;
    }

    public void setLevelParent(String levelParent) {
        this.levelParent = levelParent;
    }

    public ActiveStatus getStatus() {
        return status;
    }

    public void setStatus(ActiveStatus status) {
        this.status = status;
    }

    public Long getPrvId() {
        return prvId;
    }

    public void setPrvId(Long prvId) {
        this.prvId = prvId;
    }
}
