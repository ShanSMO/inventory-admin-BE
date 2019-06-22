package com.ims.inventory.models;

import com.ims.inventory.enums.PriviledgeType;

import javax.persistence.*;

@Entity
@Table(name = "TBL_PRIVILEGE_TYPE")
public class PrivilegeType {

    @Id
    @Column(name = "priviledge_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "prv_id")
    private Long prvId;

    @Column(name = "priviledge_name")
    private String privilegeName;

    @Column(name = "privilege_type")
    private PriviledgeType privilegeType;

    @Column(name = "parent_privilege")
    private Long parentId;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "company_join_id")
    private Company relatedCompany;

    @Column(name = "url")
    private String url;

    @Column(name = "icon")
    private String icon;

    @Column(name = "level")
    private String level;

    @Column(name = "level_parent")
    private String levelParent;

    @Transient
    private Long userId;

    @Transient
    private String assignType;


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

    public Company getRelatedCompany() {
        return relatedCompany;
    }

    public void setRelatedCompany(Company relatedCompany) {
        this.relatedCompany = relatedCompany;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAssignType() {
        return assignType;
    }

    public void setAssignType(String assignType) {
        this.assignType = assignType;
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

    public Long getPrvId() {
        return prvId;
    }

    public void setPrvId(Long prvId) {
        this.prvId = prvId;
    }
}
