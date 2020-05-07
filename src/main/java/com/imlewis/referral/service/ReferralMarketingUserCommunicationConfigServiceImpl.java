package com.imlewis.referral.service;

import java.util.Date;

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
	
	public Date getGenerationDate(long communicationId) {
		return referralMarketingUserCommunicationConfigRepository.getGenerationDate(communicationId);
	}
	
	public int isCommunicationIdPresent(long communicationId) {
		return referralMarketingUserCommunicationConfigRepository.isCommunicationIdPresent(communicationId);
	}
	
	public ReferralMarketingUserCommunicationConfig getReferralMarketingUserCommunicationConfig(long communicationId) {
		return referralMarketingUserCommunicationConfigRepository.getReferralMarketingUserCommunicationConfig(communicationId);
	}
}
