package com.imlewis.service;

import java.util.List;

import com.imlewis.model.Customer;

public interface CustomerService {
	
	Customer findOne(Long customerId);
	
	Iterable<Customer> findAll();
	
	void save(Customer customer);
	
	Customer findByEmail(String email);
	
	boolean hasRole(String role, Customer customer);
	
	List<Customer> getAllCustomer();
	
	void delete(Long customerId);
	
}
