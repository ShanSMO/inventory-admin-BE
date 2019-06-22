package com.ims.inventory.models;

import com.ims.inventory.enums.ActiveStatus;

import javax.persistence.*;

@Entity
@Table(name = "TBL_SOFTWARE_SYSTEM_SETTINGS")
public class SoftwareSystemSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "setting_key")
    private String settingKey;

    @Column(name = "setting_value")
    private String settingValue;

    @Column(name = "status")
    private ActiveStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSettingKey() {
        return settingKey;
    }

    public void setSettingKey(String settingKey) {
        this.settingKey = settingKey;
    }

    public String getSettingValue() {
        return settingValue;
    }

    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
    }

    public ActiveStatus getStatus() {
        return status;
    }

    public void setStatus(ActiveStatus status) {
        this.status = status;
    }
}
