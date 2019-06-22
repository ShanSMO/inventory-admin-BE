package com.ims.inventory.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TBL_RECENT_ACTIVITY")
public class RecentActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id")
    private Long id;

    @Column(name = "activity_name")
    private String activityName;

    @ManyToOne
    @JoinColumn(name = "company_join_id")
    private Company company;

    @Column(name = "created_date")
    private Date createdDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
