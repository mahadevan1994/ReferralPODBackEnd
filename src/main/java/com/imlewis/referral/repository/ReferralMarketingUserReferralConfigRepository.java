package com.imlewis.referral.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.imlewis.referral.model.ReferralMarketingUserReferralConfigItem;

import java.time.LocalDate;
import java.util.List;

public interface ReferralMarketingUserReferralConfigRepository extends CrudRepository<ReferralMarketingUserReferralConfigItem, Long>{

    @Query(value = "SELECT referralEnablementDate from REFERRAL_MARKETING_USER_REFERRAL_CONFIG_ITEM" , nativeQuery = true)
    LocalDate retrieveReferedDate();

    //Boolean findProfileEnabled();
}
