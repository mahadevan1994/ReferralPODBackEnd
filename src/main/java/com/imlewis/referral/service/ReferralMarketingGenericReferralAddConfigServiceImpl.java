package com.imlewis.referral.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imlewis.referral.model.ReferralMarketingGenericReferralAddConfigItem;
import com.imlewis.referral.repository.ReferralMarketingGenericReferralAddConfigRepository;

@Service
public class ReferralMarketingGenericReferralAddConfigServiceImpl implements ReferralMarketingGenericReferralAddConfigService  {
	
	@Autowired
	private ReferralMarketingGenericReferralAddConfigRepository referralMarketingGenericReferralAddConfigRepository;

	public List<ReferralMarketingGenericReferralAddConfigItem> getAllGenericReferralConfigItems() {
		return (List<ReferralMarketingGenericReferralAddConfigItem>) referralMarketingGenericReferralAddConfigRepository.findAll();
	}
	public ReferralMarketingGenericReferralAddConfigItem getAddConfigItem(long configurationId) {
		return referralMarketingGenericReferralAddConfigRepository.getAddConfigItem(configurationId);
	}
}
