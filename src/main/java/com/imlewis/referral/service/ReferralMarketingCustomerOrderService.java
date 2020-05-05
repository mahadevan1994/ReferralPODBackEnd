package com.imlewis.referral.service;

import java.util.List;

import com.imlewis.model.Customer;
import com.imlewis.model.CustomerOrder;

public interface ReferralMarketingCustomerOrderService {

	List<CustomerOrder> getAllCustomerOrderByCustomer(Customer customer);

	int getOrderCounts(Customer customer);

	double getOrderTotals(Customer customer);
}
