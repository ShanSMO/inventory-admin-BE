package com.ims.inventory.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TBL_BALANCE_SHEET")
public class BalanceSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "effective_date")
    private Date effectiveDate;

    @Column(name = "day_start_value")
    private Double dayStartValue;

    @Column(name = "day_end_value")
    private Double dayEndValue;

    @Column(name = "profit_or_loss")
    private Double profitOrLoss;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Double getDayStartValue() {
        return dayStartValue;
    }

    public void setDayStartValue(Double dayStartValue) {
        this.dayStartValue = dayStartValue;
    }

    public Double getDayEndValue() {
        return dayEndValue;
    }

    public void setDayEndValue(Double dayEndValue) {
        this.dayEndValue = dayEndValue;
    }

    public Double getProfitOrLoss() {
        return profitOrLoss;
    }

    public void setProfitOrLoss(Double profitOrLoss) {
        this.profitOrLoss = profitOrLoss;
    }
}
