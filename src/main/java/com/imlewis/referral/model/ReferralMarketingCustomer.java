package com.imlewis.referral.model;

public class ReferralMarketingCustomer {

	private Long customerId;
	
	private String customerName;
	
	private String email;
	
	private Double loyaltyPoints;
	
	private Integer orderCounts;
	
	private Double orderTotals;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getLoyaltyPoints() {
		return loyaltyPoints;
	}

	public void setLoyaltyPoints(Double loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}

	public Integer getOrderCounts() {
		return orderCounts;
	}

	public void setOrderCounts(Integer orderCounts) {
		this.orderCounts = orderCounts;
	}

	public Double getOrderTotals() {
		return orderTotals;
	}

	public void setOrderTotals(Double orderTotals) {
		this.orderTotals = orderTotals;
	}
}
