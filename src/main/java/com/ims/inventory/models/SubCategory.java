package com.ims.inventory.models;

import com.ims.inventory.dtos.ResourceFile;
import com.ims.inventory.dtos.SearchRequestDto;
import com.ims.inventory.enums.ActiveStatus;
import javax.persistence.*;

@Entity
@Table(name = "TBL_SUB_CATEGORY")
public class SubCategory extends SearchRequestDto {

    @Id
    @Column(name = "sub_category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sub_category_name")
    private String categoryName;

    @Column(name = "sub_category_description")
    private String description;

    @Column(name = "sub_category_icon")
    private String categoryImage;

    @Column(name = "sub_category_code")

    private String categoryCode;

    @Column(name = "status")
    private ActiveStatus status = ActiveStatus.ACTIVE;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "company_join_id")
    private Company company;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "category_join_id")
    private Category category;

    @Transient
    private ResourceFile resourceFile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public ActiveStatus getStatus() {
        return status;
    }

    public void setStatus(ActiveStatus status) {
        this.status = status;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ResourceFile getResourceFile() {
        return resourceFile;
    }

    public void setResourceFile(ResourceFile resourceFile) {
        this.resourceFile = resourceFile;
    }
}
