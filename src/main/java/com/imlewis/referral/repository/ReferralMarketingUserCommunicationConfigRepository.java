package com.imlewis.referral.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.imlewis.referral.model.ReferralMarketingUserCommunicationConfig;

public interface ReferralMarketingUserCommunicationConfigRepository extends CrudRepository<ReferralMarketingUserCommunicationConfig, Long>{

	@Query(value = "select referral_configuration_id from REFERRAL_MARKETING_USER_COMMUNICATION_CONFIG where customer_id = :customerId", nativeQuery = true)
	long getReferralConfigId(@Param("customerId") long customerId);
	
	@Query(value = "select generation_date from REFERRAL_MARKETING_USER_COMMUNICATION_CONFIG where communication_id = :communicationId", nativeQuery = true)
	Date getGenerationDate(@Param("communicationId") long communicationId);
	
	@Query(value = "select count(*) from REFERRAL_MARKETING_USER_COMMUNICATION_CONFIG where communication_id = :communicationId", nativeQuery = true)
	int isCommunicationIdPresent(@Param("communicationId") long communicationId);
	
	@Query(value = "select * from REFERRAL_MARKETING_USER_COMMUNICATION_CONFIG where communication_id = :communicationId", nativeQuery = true)
	ReferralMarketingUserCommunicationConfig getReferralMarketingUserCommunicationConfig(@Param("communicationId") long communicationId);
}