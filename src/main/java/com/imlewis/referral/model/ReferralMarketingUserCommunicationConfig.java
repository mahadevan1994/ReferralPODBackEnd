package com.imlewis.referral.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReferralMarketingUserCommunicationConfig implements Serializable {

	private static final long serialVersionUID = -1345626380557589113L;

	@Id
	private int communicationId;

	private Long customerId;

	private Long referralConfigurationId;

	private Date generationDate;

	public int getCommunicationId() {
		return communicationId;
	}

	public void setCommunicationId(int communicationId) {
		this.communicationId = communicationId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getReferralConfigurationId() {
		return referralConfigurationId;
	}

	public void setReferralConfigurationId(Long referralConfigurationId) {
		this.referralConfigurationId = referralConfigurationId;
	}

	public Date getGenerationDate() {
		return generationDate;
	}

	public void setGenerationDate(Date generationDate) {
		this.generationDate = generationDate;
	}

}
