package com.rcronald.ms.jsmproject.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.rcronald.ms.jsmproject.domain.Customer;
import com.rcronald.ms.jsmproject.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {
    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerServiceImpl customerService;

    @Test
    public void testRetrieveCustomers() throws Exception {
        Customer customer1 = new Customer("Ronald", "Requena", 33, new SimpleDateFormat("yyyy-MM-dd").parse("1986-06-02"));
        Customer customer2 = new Customer("David", "Susanibar", 29, new SimpleDateFormat("yyyy-MM-dd").parse("1989-03-01"));
        Customer customer3 = new Customer("Julio", "Novoa", 33, new SimpleDateFormat("yyyy-MM-dd").parse("1988-02-09"));
        Customer customer4 = new Customer("Oscar", "Pando", 33, new SimpleDateFormat("yyyy-MM-dd").parse("1983-07-12"));
        Customer customer5 = new Customer("Francisco", "Lopez", 33, new SimpleDateFormat("yyyy-MM-dd").parse("1983-07-12"));
        Collection<Customer> customers = Arrays.asList(customer1, customer2, customer3, customer4, customer5);

        when(customerRepository.findAll()).thenReturn(customers);
        assertEquals(5, customerService.retrieve().size());
    }

    @Test
    public void testCreateValidCustomer() throws Exception {
        Customer customer = new Customer("Ronald", "Requena", 33, new SimpleDateFormat("yyyy-MM-dd").parse("1986-06-02"));
        customer.setId(1);

        when(customerRepository.save(any())).thenReturn(customer);
        assertEquals("Ronald", customerService.create(customer).getName());
        assertEquals("Requena", customerService.create(customer).getLastName());
        assertEquals(33, customerService.create(customer).getAge());
        assertTrue(customerService.create(customer).getId()>0);
    }
}
