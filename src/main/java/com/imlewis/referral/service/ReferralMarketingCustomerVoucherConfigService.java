package com.imlewis.referral.service;

import com.imlewis.referral.model.ReferralMarketingCustomerVoucherConfig;

public interface ReferralMarketingCustomerVoucherConfigService {

	void save(ReferralMarketingCustomerVoucherConfig referralMarketingCustomerVoucherConfig);
	
	ReferralMarketingCustomerVoucherConfig findByVoucherCode(String voucherCode);
}
