package com.rcronald.ms.jsmproject.web;

import java.net.URI;
import java.util.Collection;

import com.rcronald.ms.jsmproject.domain.Customer;
import com.rcronald.ms.jsmproject.domain.CustomerStatistic;
import com.rcronald.ms.jsmproject.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.validation.Valid;

/**
 * Responsible for REST operations involving Customers. Creating,
 * retrieving and calculate data.
 * 
 * @author rcronald
 */
@Api(tags = "customer")
@RestController
@Validated
@RequestMapping("api/v1/")
public class CustomerResource {
	
	@Autowired
	private CustomerService customerService;

	@ApiOperation("Create a new Customer.")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "OK") })
	@RequestMapping(value = "customer", method = RequestMethod.POST,
					consumes = MediaType.APPLICATION_JSON_VALUE, 
					produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Customer> createCustomer(
		@ApiParam(name="student", value = "The student.", required = true)
		@Valid
		@RequestBody Customer customer
	) {
		Customer newCustomer = customerService.create(customer);

		return ResponseEntity.created(customerURI(newCustomer.getId())).body(newCustomer);
	}

	@ApiOperation("Return average age and standar deviation about all Customer data.")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "OK") })
	@RequestMapping(value = "customer/kpi", method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public CustomerStatistic getStatisticsCustomerData() {
		return customerService.kpi();
	}

	@ApiOperation("Retrieves the list of Customers recorded in the system.")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "OK") })
	@RequestMapping(value = "customer", method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public Collection<Customer> getCustomers() {
		return customerService.retrieve();
	}

	private static URI customerURI(Integer customerId) {
		return toUri("/api/customer/{id}", customerId);
	}

	private static URI toUri(String path, Integer studentId) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path(path)
				.buildAndExpand(studentId).toUri();
	}
}