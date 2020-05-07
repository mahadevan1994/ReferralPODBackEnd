package com.imlewis.referral.service;

import java.util.Date;

import com.imlewis.referral.model.ReferralMarketingUserReferralConfigItem;

public interface ReferralMarketingUserReferralConfigService {

	void save(ReferralMarketingUserReferralConfigItem referralMarketingUserReferralConfigItem);
	
	Date retrieveReferedDate();
	
	Boolean isReferralEnablement();

	int getReferralFrequency();
	
	int getReferralLinkExpiryInDays();
	
	int getReferralBenefitExpiryInDays();
	
	ReferralMarketingUserReferralConfigItem findFirst();
}
