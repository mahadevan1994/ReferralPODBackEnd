package com.imlewis.referral.service;

import java.util.List;

import com.imlewis.referral.model.ReferralMarketingGenericReferralAddConfigItem;

public interface ReferralMarketingGenericReferralAddConfigService {

	List<ReferralMarketingGenericReferralAddConfigItem> getAllGenericReferralConfigItems();
	
	ReferralMarketingGenericReferralAddConfigItem getAddConfigItem(long configurationId);
	
	void save(ReferralMarketingGenericReferralAddConfigItem referralMarketingGenericReferralAddConfigItem);
}
