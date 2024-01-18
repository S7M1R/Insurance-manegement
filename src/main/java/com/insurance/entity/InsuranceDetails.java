package com.insurance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceDetails {
    @Id
    private Long insuranceId;
    private Long userId;
    private Date creationDate;
    private Date expiryDate;

    private double premiums;
    private boolean eligibility;
    private int waitingPeriod;
    private String coverageLevel;

    @Enumerated(EnumType.STRING)
    private InsuranceType insuranceType;

    public void setInsuranceDetailsForType(InsuranceType insuranceType) {
        if (insuranceType == InsuranceType.LIFE_INSURANCE) {
            this.insuranceType = InsuranceType.valueOf("LIFE_INSURANCE");
            this.premiums= 1000.00;
            this.eligibility= true;
            this.waitingPeriod= 60;
            this.coverageLevel = "basic";
        } else if (insuranceType == InsuranceType.LONG_TERM_LIFE_INSURANCE) {
            this.insuranceType = InsuranceType.valueOf("LONG_TERM_LIFE_INSURANCE");
            this.premiums = 3000.00;
            this.eligibility = true;
            this.waitingPeriod = 30;
            this.coverageLevel = "comprehensive";
        }
    }
}
