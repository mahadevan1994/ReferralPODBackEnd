package com.imlewis.referral.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imlewis.model.Customer;
import com.imlewis.model.CustomerOrder;
import com.imlewis.repository.CustomerOrderRepository;

@Service
public class ReferralMarketingCustomerOrderServiceImpl implements ReferralMarketingCustomerOrderService {

	@Autowired
	private CustomerOrderRepository customerOrderRepository;

	public List<CustomerOrder> getAllCustomerOrderByCustomer(Customer customer) {
		return customerOrderRepository.findAllByCustomer(customer);
	}

	public int getOrderCounts(Customer customer) {
		List<CustomerOrder> orderList = getAllCustomerOrderByCustomer(customer);
		return orderList.size();
	}

	public double getOrderTotals(Customer customer) {
		double orderValue = 0.00d;
		List<CustomerOrder> orderList = getAllCustomerOrderByCustomer(customer);
		for (CustomerOrder customerOrder : orderList) {
			orderValue += customerOrder.getOrderTotalPrice();
		}
		return orderValue;
	}
}
