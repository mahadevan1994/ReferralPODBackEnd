package com.imlewis.referral.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imlewis.referral.model.ReferralMarketingUserCommunicationConfig;
import com.imlewis.referral.repository.ReferralMarketingUserCommunicationConfigRepository;

@Service
public class ReferralMarketingUserCommunicationConfigServiceImpl implements ReferralMarketingUserCommunicationConfigService {

	@Autowired
	ReferralMarketingUserCommunicationConfigRepository referralMarketingUserCommunicationConfigRepository;
	
	public void save(ReferralMarketingUserCommunicationConfig referralMarketingUserCommunicationConfig){
		referralMarketingUserCommunicationConfigRepository.save(referralMarketingUserCommunicationConfig);
	}
	
	public long getReferralConfigId(long customerId) {
		return referralMarketingUserCommunicationConfigRepository.getReferralConfigId(customerId);
	}
}
