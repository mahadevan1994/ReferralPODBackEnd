package com.imlewis.referral.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class ReferralMarketingCustomerVoucherConfig implements Serializable{
	
	private static final long serialVersionUID = -6922461044823943143L;
	
	@Id
    @GeneratedValue
    private Long id;
	
	private String voucherCode;
	
	private String redeemStatus;
	
	private int referralAmount;
	
	public int getReferralAmount() {
		return referralAmount;
	}

	public void setReferralAmount(int referralAmount) {
		this.referralAmount = referralAmount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVoucherCode() {
		return voucherCode;
	}

	public void setVoucherCode(String voucherCode) {
		this.voucherCode = voucherCode;
	}

	public String getRedeemStatus() {
		return redeemStatus;
	}

	public void setRedeemStatus(String redeemStatus) {
		this.redeemStatus = redeemStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
