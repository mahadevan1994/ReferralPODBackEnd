package com.imlewis.referral.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.imlewis.referral.model.ReferralMarketingUserCommunicationConfig;

public interface ReferralMarketingUserCommunicationConfigRepository extends CrudRepository<ReferralMarketingUserCommunicationConfig, Long>{

	@Query(value = "select referral_configuration_id from REFERRAL_MARKETING_USER_COMMUNICATION_CONFIG where customer_id = :customerId", nativeQuery = true)
	long getReferralConfigId(@Param("customerId") long customerId);
}