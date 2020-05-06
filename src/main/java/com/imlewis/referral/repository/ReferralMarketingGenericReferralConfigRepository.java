package com.imlewis.referral.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.imlewis.referral.model.ReferralMarketingGenericReferralConfigItem;

public interface ReferralMarketingGenericReferralConfigRepository extends CrudRepository<ReferralMarketingGenericReferralConfigItem, Long>{
    @Query(value = "SELECT Minimum_Order_Count from REFERRAL_MARKETING_GENERIC_REFERRAL_CONFIG_ITEM" , nativeQuery = true)
    int findMinimumOrderCount();
    
    @Query(value = "SELECT Total_Business from REFERRAL_MARKETING_GENERIC_REFERRAL_CONFIG_ITEM" , nativeQuery = true)
    Double findMinimumOrderTotal();
}
