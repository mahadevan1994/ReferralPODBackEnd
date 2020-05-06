package com.imlewis.referral.scheduler;

import com.imlewis.model.Customer;
import com.imlewis.referral.model.ReferralMarketingGenericReferralConfigItem;
import com.imlewis.referral.model.ReferralMarketingUserReferralConfigItem;
import com.imlewis.referral.repository.ReferralMarketingGenericReferralConfigRepository;
import com.imlewis.referral.repository.ReferralMarketingUserReferralConfigRepository;
import com.imlewis.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ReferralMarketingScheduler {

    private static final Logger log = LoggerFactory.getLogger(ReferralMarketingScheduler.class);
    private final ReferralMarketingUserReferralConfigRepository userReferralMarketingRepo;
    private final ReferralMarketingGenericReferralConfigRepository genericReferalConfigRepo;

    private final Customer customer = new Customer();
    private final CustomerService customerService;
    private final ReferralMarketingUserReferralConfigItem userReferal = new ReferralMarketingUserReferralConfigItem();
    private final ReferralMarketingGenericReferralConfigItem referralMarketingGenericReferralConfigItem = new ReferralMarketingGenericReferralConfigItem();
    
    
    @Autowired
    public ReferralMarketingScheduler(ReferralMarketingUserReferralConfigRepository userReferralMarketingRepo, ReferralMarketingGenericReferralConfigRepository genericReferalConfigRepo, CustomerService customerService) {
        this.userReferralMarketingRepo = userReferralMarketingRepo;
        this.genericReferalConfigRepo = genericReferalConfigRepo;
        this.customerService = customerService;
    }

    @Scheduled(cron = "1 0 0-5 13 * *")
    private void execute() {
        LocalDate referedDate = userReferralMarketingRepo.retrieveReferedDate();
        //checking referal enabled
        if(userReferal.isReferralEnablement()) {

            //condition to check user registered date is after the referralEnablementDate
            if(customer.getRegisterDate().isAfter(referedDate))
                //condition to check customer order meets the minimum order count configured
            if (customer.getOrderCounts() >= genericReferalConfigRepo.findMinimumOrderCount()) {
                List<Customer> customerList = customerService.getAllCustomer();
                for (int i = 0; i < customerList.size(); i++) {
                    //checks refereddate is future date from today.
                    if (referedDate.isAfter(LocalDate.now())) {

                    }
                }
            }
        }

        log.debug("Referral scheduled!");
    }
}
