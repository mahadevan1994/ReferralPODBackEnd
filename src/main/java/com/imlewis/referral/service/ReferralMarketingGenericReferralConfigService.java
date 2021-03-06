package com.imlewis.referral.service;

import com.imlewis.referral.model.ReferralMarketingGenericReferralConfigItem;

public interface ReferralMarketingGenericReferralConfigService {

	void save(ReferralMarketingGenericReferralConfigItem ReferralMarketingGenericReferralConfigItem);
	
	int findMinimumOrderCount();
	
	Double findMinimumOrderTotal();
	
	ReferralMarketingGenericReferralConfigItem findFirst();
}
