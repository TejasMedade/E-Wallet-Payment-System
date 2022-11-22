/**
 * 
 */
package com.masai.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.AdminException;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.model.Customer;
import com.masai.services.CustomerService;

/**
 * @author tejas
 *
 */

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/add")
	public ResponseEntity<Customer> addCustomerHandler(@Valid @RequestBody Customer customer) throws CustomerException {

		Customer added_customer = customerService.addCustomer(customer);

		return new ResponseEntity<Customer>(added_customer, HttpStatus.ACCEPTED);

	}

	@PutMapping("/update")
	public ResponseEntity<Customer> updateCustomerHandler(@RequestParam String key,
			@Valid @RequestBody Customer customer) throws CustomerException, LoginException {

		Customer updated_customer = customerService.updateCustomer(key, customer);

		return new ResponseEntity<Customer>(updated_customer, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> removeCustomerHandler(@RequestParam String key,
			@Valid @RequestParam String mobileNumber) throws CustomerException, LoginException {

		String result = customerService.removeCustomer(key, mobileNumber);

		return new ResponseEntity<String>(result, HttpStatus.OK);

	}

	@GetMapping("/view")
	public ResponseEntity<Customer> viewCustomerHandler(@RequestParam String key,
			@Valid @RequestParam String mobileNumber) throws CustomerException, LoginException {

		Customer customer = customerService.viewCustomer(key, mobileNumber);

		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	// Check for Admin Role
	@GetMapping("/viewall")
	public ResponseEntity<List<Customer>> viewAllCustomersHandler(@RequestParam String key)
			throws CustomerException, LoginException, AdminException {

		List<Customer> listofcustomers = customerService.viewAllCustomers(key);

		return new ResponseEntity<List<Customer>>(listofcustomers, HttpStatus.OK);
	}

}
