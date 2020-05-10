package com.imlewis.referral.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imlewis.model.Customer;
import com.imlewis.referral.model.ReferralMarketingGenericReferralAddConfigItem;
import com.imlewis.referral.model.ReferralMarketingUserCommunicationConfig;
import com.imlewis.service.CustomerService;

@Service
public class ReferralMarketingDualIncentiveServiceImpl implements ReferralMarketingDualIncentiveService {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ReferralMarketingUserCommunicationConfigService referralMarketingUserCommunicationConfigService;

	@Autowired
	ReferralMarketingGenericReferralAddConfigService referralMarketingGenericReferralAddConfigService;

	@Override
	public void checkAndApplyLoyalty(Long communicationId) {
		ReferralMarketingUserCommunicationConfig referralMarketingUserCommunicationConfig = referralMarketingUserCommunicationConfigService
				.getReferralMarketingUserCommunicationConfig(communicationId);
		if (referralMarketingUserCommunicationConfig != null) {
			Long referralConfigurationId = referralMarketingUserCommunicationConfig.getReferralConfigurationId();
			ReferralMarketingGenericReferralAddConfigItem addConfigItem = referralMarketingGenericReferralAddConfigService
					.getAddConfigItem(referralConfigurationId);
			if (addConfigItem != null && addConfigItem.getProgramType().equals("dual")) {
				Long customerId = referralMarketingUserCommunicationConfig.getCustomerId();
				Customer customer = customerService.findOne(customerId);
				if (customer != null) {
					customer.setLoyaltyPoints(customer.getLoyaltyPoints() + addConfigItem.getDualReferralAmount());
					customerService.save(customer);
				}
			}
		}
	}
}