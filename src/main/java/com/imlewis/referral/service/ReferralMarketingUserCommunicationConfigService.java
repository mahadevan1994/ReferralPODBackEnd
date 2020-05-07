package com.imlewis.referral.service;

import com.imlewis.referral.model.ReferralMarketingUserCommunicationConfig;

public interface ReferralMarketingUserCommunicationConfigService {

	void save(ReferralMarketingUserCommunicationConfig ReferralMarketingUserCommunicationConfig);
	
	long getReferralConfigId(long customerId);
	
}
