package com.imlewis.service;

import com.imlewis.model.Customer;

public interface EmailSenderService {

	void sendActiveCode(Customer customer);
	
	void sendEmail(Customer customer, int communicationId);
	
	void sendEmail(Customer customer, String referredEmail);
}