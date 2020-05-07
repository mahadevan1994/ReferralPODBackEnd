package com.imlewis.referral.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imlewis.referral.model.ReferralMarketingUserReferralConfigItem;
import com.imlewis.referral.repository.ReferralMarketingUserReferralConfigRepository;

@Service
public class ReferralMarketingUserReferralConfigServiceImpl implements ReferralMarketingUserReferralConfigService{
	
	@Autowired
	private ReferralMarketingUserReferralConfigRepository referralMarketingUserReferralConfigRepository;
	
	public void save(ReferralMarketingUserReferralConfigItem referralMarketingUserReferralConfigItem){
		referralMarketingUserReferralConfigRepository.save(referralMarketingUserReferralConfigItem);
	}
	
	public Date retrieveReferedDate() {
		return referralMarketingUserReferralConfigRepository.retrieveReferedDate();
	}
	
	public Boolean isReferralEnablement() {
		return referralMarketingUserReferralConfigRepository.isReferralEnablement();
	}

	public int getReferralFrequency() {
		return referralMarketingUserReferralConfigRepository.getReferralFrequency();
	}
	
	public int getReferralLinkExpiryInDays() {
		return referralMarketingUserReferralConfigRepository.getReferralLinkExpiryInDays();
	}
	public int getReferralBenefitExpiryInDays() {
		return referralMarketingUserReferralConfigRepository.getReferralBenefitExpiryInDays();
	}
}
