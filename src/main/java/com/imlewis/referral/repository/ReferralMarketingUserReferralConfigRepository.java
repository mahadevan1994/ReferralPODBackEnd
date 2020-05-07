package com.imlewis.referral.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.imlewis.referral.model.ReferralMarketingUserReferralConfigItem;

public interface ReferralMarketingUserReferralConfigRepository extends CrudRepository<ReferralMarketingUserReferralConfigItem, Long>{

    @Query(value = "SELECT Referral_Enablement_Date from REFERRAL_MARKETING_USER_REFERRAL_CONFIG_ITEM" , nativeQuery = true)
    Date retrieveReferedDate();

    @Query(value = "SELECT Referral_Enablement from REFERRAL_MARKETING_USER_REFERRAL_CONFIG_ITEM" , nativeQuery = true)
    Boolean isReferralEnablement();

    @Query(value = "SELECT referralFrequency from REFERRAL_MARKETING_USER_REFERRAL_CONFIG_ITEM" , nativeQuery = true)
    int getReferralFrequency();
    
    //Boolean findProfileEnabled();
}
