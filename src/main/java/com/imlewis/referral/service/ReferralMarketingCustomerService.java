package com.imlewis.referral.service;

import java.util.List;

import com.imlewis.model.Customer;

public interface ReferralMarketingCustomerService {
	List<Customer> getFilteredCustomers();
}
