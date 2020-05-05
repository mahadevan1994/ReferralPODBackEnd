package com.imlewis.referral.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class ReferralMarketingGenericReferralConfigItem implements Serializable{

    private static final long serialVersionUID = -1345626380557589113L;

    @Id
    @GeneratedValue
    private Long genericReferralId;

    @NotEmpty(message = "Please fill in minimumOrderCount")
    private int minimumOrderCount;
    private int totalBusiness;
    private String programType;
    private String benefitType;
    private String referralMessage;
    private double referralAmount;

    @Transient
    private MultipartFile sliderImage;

    public Long getGenericReferralId() {
        return genericReferralId;
    }

    public void setGenericReferralId(Long genericReferralId) {
        this.genericReferralId = genericReferralId;
    }

    public int getMinimumOrderCount() {
        return minimumOrderCount;
    }

    public void setMinimumOrderCount(int minimumOrderCount) {
        this.minimumOrderCount = minimumOrderCount;
    }


    public int getTotalBusiness() {
        return totalBusiness;
    }

    public void setTotalBusiness(int totalBusiness) {
        this.totalBusiness = totalBusiness;
    }

	public String getProgramType() {
		return programType;
	}

	public void setProgramType(String programType) {
		this.programType = programType;
	}

	public String getBenefitType() {
		return benefitType;
	}

	public void setBenefitType(String benefitType) {
		this.benefitType = benefitType;
	}

	public String getReferralMessage() {
		return referralMessage;
	}

	public void setReferralMessage(String referralMessage) {
		this.referralMessage = referralMessage;
	}

	public double getReferralAmount() {
		return referralAmount;
	}

	public void setReferralAmount(double referralAmount) {
		this.referralAmount = referralAmount;
	}
    
}

