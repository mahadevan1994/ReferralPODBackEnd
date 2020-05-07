package com.imlewis.referral.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imlewis.referral.model.ReferralMarketingGenericReferralConfigItem;
import com.imlewis.referral.repository.ReferralMarketingGenericReferralConfigRepository;

@Service
public class ReferralMarketingGenericReferralConfigServiceImpl implements ReferralMarketingGenericReferralConfigService {

	@Autowired
	private ReferralMarketingGenericReferralConfigRepository referralMarketingGenericReferralConfigRepository;
	
	public void save(ReferralMarketingGenericReferralConfigItem referralMarketingGenericReferralConfigItem){
		referralMarketingGenericReferralConfigRepository.save(referralMarketingGenericReferralConfigItem);
	}
	
	public int findMinimumOrderCount() {
		return referralMarketingGenericReferralConfigRepository.findMinimumOrderCount();
	}
	
	public Double findMinimumOrderTotal() {
		return referralMarketingGenericReferralConfigRepository.findMinimumOrderTotal();
	}

	public ReferralMarketingGenericReferralConfigItem findFirst() {
		return referralMarketingGenericReferralConfigRepository.findOne(1l);
	}
}
