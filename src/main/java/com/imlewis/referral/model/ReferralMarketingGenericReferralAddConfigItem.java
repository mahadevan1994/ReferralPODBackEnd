package com.imlewis.referral.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class ReferralMarketingGenericReferralAddConfigItem implements Serializable{
	
	private static final long serialVersionUID = -6922461044823943143L;

	@Id
    @GeneratedValue
    private Long configurationId;

    @NotEmpty(message = "Please select a program type")
	private String programType;
    @NotEmpty(message = "Please select a benefit type")
	private String benefitType;
	private String referralMessage;
	@NotNull(message = "Please enter the referral amount")
	private double referralAmount;
	
	private Long giftSelect;
	
	public Long getConfigurationId() {
		return configurationId;
	}
	public void setConfigurationId(Long configurationId) {
		this.configurationId = configurationId;
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
	public Long getGiftSelect() {
		return giftSelect;
	}
	public void setGiftSelect(Long giftSelect) {
		this.giftSelect = giftSelect;
	}
}
