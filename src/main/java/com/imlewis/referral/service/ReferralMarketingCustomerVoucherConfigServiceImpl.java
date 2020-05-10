package com.imlewis.referral.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imlewis.referral.model.ReferralMarketingCustomerVoucherConfig;
import com.imlewis.referral.repository.ReferralMarketingCustomerVoucherConfigRepository;

@Service
public class ReferralMarketingCustomerVoucherConfigServiceImpl implements ReferralMarketingCustomerVoucherConfigService {

	@Autowired
	ReferralMarketingCustomerVoucherConfigRepository referralMarketingCustomerVoucherConfigRepository;
	
	public void save(ReferralMarketingCustomerVoucherConfig referralMarketingCustomerVoucherConfig) {
		referralMarketingCustomerVoucherConfigRepository.save(referralMarketingCustomerVoucherConfig);
	}
	
	public ReferralMarketingCustomerVoucherConfig findByVoucherCode(String voucherCode) {
		return referralMarketingCustomerVoucherConfigRepository.findByVoucherCode(voucherCode);
	}
	
}
