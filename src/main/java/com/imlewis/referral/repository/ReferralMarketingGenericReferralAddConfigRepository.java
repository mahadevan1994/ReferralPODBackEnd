package com.imlewis.referral.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.imlewis.referral.model.ReferralMarketingGenericReferralAddConfigItem;

public interface ReferralMarketingGenericReferralAddConfigRepository extends CrudRepository<ReferralMarketingGenericReferralAddConfigItem, Long>{
	
	@Query(value = "select * from REFERRAL_MARKETING_GENERIC_REFERRAL_ADD_CONFIG_ITEM where configuration_id = :configurationId", nativeQuery = true)
	ReferralMarketingGenericReferralAddConfigItem getAddConfigItem(@Param("configurationId") Long configurationId);
}
