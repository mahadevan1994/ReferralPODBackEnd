package com.imlewis.referral.service;

import java.util.Date;

import com.imlewis.referral.model.ReferralMarketingUserCommunicationConfig;

public interface ReferralMarketingUserCommunicationConfigService {

	void save(ReferralMarketingUserCommunicationConfig ReferralMarketingUserCommunicationConfig);
	
	long getReferralConfigId(long customerId);
	
	Date getGenerationDate(long communicationId);
	
	int isCommunicationIdPresent(long communicationId);
	
	ReferralMarketingUserCommunicationConfig getReferralMarketingUserCommunicationConfig(long communicationId);
}
