package com.imlewis.referral.scheduler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.imlewis.model.Customer;
import com.imlewis.referral.service.ReferralMarketingCustomerService;
import com.imlewis.referral.service.ReferralMarketingUserReferralConfigService;

@Component
public class ReferralMarketingScheduler {

    private static final Logger log = LoggerFactory.getLogger(ReferralMarketingScheduler.class);
    
    @Autowired
    private ReferralMarketingUserReferralConfigService referralMarketingUserReferralConfigService;
    
    @Autowired
    private ReferralMarketingCustomerService referralMarketingCustomerService;
    
    @Scheduled(cron = "1 0 0-5 13 * *")
    private void execute() {
        //checking referral enabled
        if(referralMarketingUserReferralConfigService.isReferralEnablement()) {
            List<Customer> filteredCustomers = referralMarketingCustomerService.getFilteredCustomers();
            //Proceed from here
        }

        log.debug("Referral scheduled!");
    }
}
