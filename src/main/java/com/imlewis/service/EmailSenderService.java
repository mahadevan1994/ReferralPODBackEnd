package com.imlewis.service;

import com.imlewis.model.Customer;
import com.imlewis.referral.model.ReferralMarketingUserCommunicationConfig;

public interface EmailSenderService {

	void sendActiveCode(Customer customer);
	
	void sendEmail(Customer customer, int communicationId);
	
	void sendEmail(ReferralMarketingUserCommunicationConfig userCommunicationConfigItem, String referredEmail);
	
	void sendMail(String toAddress, String subject, String msgBody);
}