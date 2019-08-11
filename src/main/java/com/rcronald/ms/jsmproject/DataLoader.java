package com.rcronald.ms.jsmproject;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;

import com.rcronald.ms.jsmproject.domain.Customer;
import com.rcronald.ms.jsmproject.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

	private CustomerRepository customerRepository;

	@Autowired
	public DataLoader(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
        Customer customer1 = new Customer("Ronald", "Requena", 33, new SimpleDateFormat("yyyy-MM-dd").parse("1986-06-02"));
        Customer customer2 = new Customer("David", "Susanibar", 29, new SimpleDateFormat("yyyy-MM-dd").parse("1989-03-01"));
        Customer customer3 = new Customer("Julio", "Novoa", 33, new SimpleDateFormat("yyyy-MM-dd").parse("1988-02-09"));
        Customer customer4 = new Customer("Oscar", "Pando", 33, new SimpleDateFormat("yyyy-MM-dd").parse("1983-07-12"));
        Customer customer5 = new Customer("Francisco", "Lopez", 33, new SimpleDateFormat("yyyy-MM-dd").parse("1983-07-12"));

        Collection<Customer> customers = Arrays.asList(customer1, customer2, customer3, customer4, customer5);
		customerRepository.saveAll(customers);
	}
}