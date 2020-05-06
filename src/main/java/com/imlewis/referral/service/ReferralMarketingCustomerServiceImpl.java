package com.imlewis.referral.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imlewis.model.Customer;
import com.imlewis.service.CustomerService;

@Service
public class ReferralMarketingCustomerServiceImpl implements ReferralMarketingCustomerService {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ReferralMarketingUserReferralConfigService referralMarketingUserReferralConfigService;

	@Autowired
	private ReferralMarketingGenericReferralConfigService referralMarketingGenericReferralConfigService;

	@Override
	public List<Customer> getFilteredCustomers() {
		// Implement the customer filter list here
		List<Customer> filteredList = new ArrayList<Customer>();
		List<Customer> customerList = customerService.getAllCustomer();

		for (Customer customer : customerList) {
			Date referedDate = referralMarketingUserReferralConfigService.retrieveReferedDate();
			// condition to check user registered date is after the referralEnablementDate
			// condition to check customer order meets the minimum order count configured
			if (null != (customer.getRegisterDate()) && customer.getRegisterDate().after(referedDate)
					&& customer.getOrderCounts() >= referralMarketingGenericReferralConfigService
							.findMinimumOrderCount()
					&& customer.getOrderTotals() >= referralMarketingGenericReferralConfigService
							.findMinimumOrderTotal()) {
				filteredList.add(customer);
			}
		}
		return filteredList;
	}
}