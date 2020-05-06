package com.imlewis.referral.service;

import java.time.LocalDate;

import com.imlewis.referral.model.ReferralMarketingUserReferralConfigItem;

public interface ReferralMarketingUserReferralConfigService {

	void save(ReferralMarketingUserReferralConfigItem referralMarketingUserReferralConfigItem);
	
	LocalDate retrieveReferedDate();
	
	Boolean isReferralEnablement();
}
