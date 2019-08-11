package com.rcronald.ms.jsmproject.service;

import java.util.Collection;

import com.rcronald.ms.jsmproject.domain.Customer;
import com.rcronald.ms.jsmproject.domain.CustomerStatistic;


/** 
 * Various processing logic related to the Customer.
 * 
 * @author rcronald
 */
public interface CustomerService {

	/**
	 * Creates a new customer to the system.
	 * 
	 * @param customer the customer.
	 * 
	 * @return the newly created customer.
	 */
    public Customer create(Customer customer);
    
    /**
	 * Retrieve the customer registed in the system.
	 * 
	 * @return the list of customers.
	 */
    public Collection<Customer> retrieve();
    
    /**
	 * Return statistic data about the customers.
	 * 
	 * @return the statistic customer data.
	 */
	public CustomerStatistic kpi();
}