package com.imlewis.referral.scheduler;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.imlewis.model.Customer;
import com.imlewis.referral.model.ReferralMarketingUserCommunicationConfig;
import com.imlewis.referral.service.ReferralMarketingCustomerService;
import com.imlewis.referral.service.ReferralMarketingGenericReferralAddConfigService;
import com.imlewis.referral.service.ReferralMarketingUserCommunicationConfigService;
import com.imlewis.referral.service.ReferralMarketingUserReferralConfigService;
import com.imlewis.service.EmailSenderService;

@Component
public class ReferralMarketingScheduler {

	private static final Logger log = LoggerFactory.getLogger(ReferralMarketingScheduler.class);

	@Autowired
	private ReferralMarketingUserReferralConfigService referralMarketingUserReferralConfigService;

	@Autowired
	private ReferralMarketingGenericReferralAddConfigService referralMarketingGenericReferralAddConfigService;

	@Autowired
	private ReferralMarketingCustomerService referralMarketingCustomerService;

	@Autowired
	private ReferralMarketingUserCommunicationConfigService referralMarketingUserCommunicationConfigService;

	@Autowired
	private EmailSenderService emailSenderService;

	@Scheduled(cron = "0 0 1 * * ?")
	private void execute() {
		// checking referral enabled
		if (referralMarketingUserReferralConfigService.isReferralEnablement()) {
			List<Customer> filteredCustomers = referralMarketingCustomerService.getFilteredCustomers();
			for (Customer customer : filteredCustomers) {
				ReferralMarketingUserCommunicationConfig referralMarketingUserCommunicationConfig = new ReferralMarketingUserCommunicationConfig();
				referralMarketingUserCommunicationConfig
						.setCommunicationId(new Random().nextInt(9999999) == 0 ? 1 : new Random().nextInt(9999999));
				referralMarketingUserCommunicationConfig.setCustomerId(customer.getCustomerId());
				referralMarketingUserCommunicationConfig.setGenerationDate(new Date());
				int addedConfigurationCount = referralMarketingGenericReferralAddConfigService
						.getAllGenericReferralConfigItems().size();
				referralMarketingUserCommunicationConfig
						.setReferralConfigurationId(Long.valueOf(new Random().nextInt(addedConfigurationCount)) == 0 ? 1
								: Long.valueOf(new Random().nextInt(addedConfigurationCount)));
				referralMarketingUserCommunicationConfigService.save(referralMarketingUserCommunicationConfig);
				emailSenderService.sendEmail(customer, referralMarketingUserCommunicationConfig.getCommunicationId());
			}
		}

		log.debug("Referral scheduled!");
	}
}
