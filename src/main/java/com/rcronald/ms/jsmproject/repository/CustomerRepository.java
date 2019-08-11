package com.rcronald.ms.jsmproject.repository;

import java.util.Collection;

import com.rcronald.ms.jsmproject.domain.Customer;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
	Collection<Customer> findAll();
}