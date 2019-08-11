package com.rcronald.ms.jsmproject.service;

import java.util.Collection;
import java.util.DoubleSummaryStatistics;
import java.util.stream.Collectors;

import com.rcronald.ms.jsmproject.domain.Customer;
import com.rcronald.ms.jsmproject.domain.CustomerStatistic;
import com.rcronald.ms.jsmproject.repository.CustomerRepository;
import com.rcronald.ms.jsmproject.util.StatisticOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private StatisticOperation statOperation;
    
	@Override
	public Customer create(Customer dto) {
        Customer newCustomer = new Customer(dto.getName(), dto.getLastName(),
            dto.getAge(), dto.getDateOfBirth());

		return  customerRepository.save(newCustomer);
    }

    @Override
    public Collection<Customer> retrieve() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerStatistic kpi() {
        Collection<Customer> customers = customerRepository.findAll();


        Collection<Integer> customerAges = customers.stream().map(Customer::getAge).collect(Collectors.toList());

        double customerAgeAverage = statOperation.computeAverage(customerAges);
        DoubleSummaryStatistics customerStats = statOperation.computeStatistics(customerAges);
        double customerSD = statOperation.computeStandardDeviation(customerAges);

        return new CustomerStatistic(customerAgeAverage, customerStats.getCount(), customerStats.getMin(), 
            customerStats.getMax(), customerSD, customerStats.getSum());
    }	
}