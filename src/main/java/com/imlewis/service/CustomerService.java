package com.imlewis.service;

import java.util.List;

import com.imlewis.model.Customer;
import com.imlewis.model.OmniAccount;
import com.imlewis.model.RichRelevanceMockForFashion;

public interface CustomerService {
	
	Customer findOne(Long customerId);
	
	Iterable<Customer> findAll();
	
	void save(Customer customer);
	
	void activeAccount(String codeStr);
	
	Customer findByEmail(String email);
	
	boolean hasRole(String role, Customer customer);
	
	List<Customer> getAllCustomer();
	
	void delete(Long customerId);
	
}
