package com.imlewis.referral.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.imlewis.referral.model.ReferralMarketingUserReferralConfigItem;

public interface ReferralMarketingUserReferralConfigRepository extends CrudRepository<ReferralMarketingUserReferralConfigItem, Long>{

    @Query(value = "SELECT referralEnablementDate from REFERRAL_MARKETING_USER_REFERRAL_CONFIG_ITEM" , nativeQuery = true)
    LocalDate retrieveReferedDate();

    @Query(value = "SELECT referralEnablement from REFERRAL_MARKETING_USER_REFERRAL_CONFIG_ITEM" , nativeQuery = true)
    Boolean isReferralEnablement();
    
    //Boolean findProfileEnabled();
}
