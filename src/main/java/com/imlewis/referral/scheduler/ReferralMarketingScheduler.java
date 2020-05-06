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
    @Autowired
    private final ReferralMarketingUserReferralConfigRepository userReferralMarketingRepo;
    @Autowired
    private final ReferralMarketingGenericReferralConfigRepository genericReferalConfigRepo;

    @Autowired
    private final Customer customer;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ReferralMarketingUserReferralConfigItem userReferal;

    public ReferralMarketingScheduler(ReferralMarketingUserReferralConfigRepository userReferralMarketingRepo, ReferralMarketingGenericReferralConfigRepository genericReferalConfigRepo, ReferralMarketingGenericReferralConfigItem genericReferralMarketingRepo, Customer customer) {
        this.userReferralMarketingRepo = userReferralMarketingRepo;
        this.genericReferalConfigRepo = genericReferalConfigRepo;
        this.customer = customer;
    }

    @Scheduled(cron = "[Seconds] [Minutes] [Hours] [Day of month] [Month] [Day of week] [Year]")
    private void execute() {

        if(userReferal.isReferralEnablement()) {
            if (customer.getOrderCounts() >= genericReferalConfigRepo.findMinimumOrderCount()) {
                List<Customer> customerList = customerService.getAllCustomer();
                int ordercount = customer.getOrderCounts();
                double orderTotal = customer.getOrderTotals();
                for (int i = 0; i < customerList.size(); i++) {
                    LocalDate referedDate = userReferralMarketingRepo.retrieveReferedDate();

                    if (referedDate.isAfter(LocalDate.now())) {

                    }
                }
            }
        }

        log.debug("Referral scheduled!");
    }
}
