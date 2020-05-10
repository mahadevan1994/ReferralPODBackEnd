package com.imlewis.referral.repository;

import org.springframework.data.repository.CrudRepository;

import com.imlewis.referral.model.ReferralMarketingCustomerVoucherConfig;

public interface ReferralMarketingCustomerVoucherConfigRepository extends CrudRepository<ReferralMarketingCustomerVoucherConfig, Long> {

	public ReferralMarketingCustomerVoucherConfig findByVoucherCode(String voucherCode);
}
