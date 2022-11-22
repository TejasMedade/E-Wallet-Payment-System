/**
 * 
 */
package com.masai.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.CurrentCustomerSessionException;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.LogoutException;
import com.masai.exceptions.UserException;
import com.masai.model.CurrentCustomerSession;
import com.masai.model.Customer;
import com.masai.model.User;
import com.masai.services.LoginLogoutCustomerService;

/**
 * @author tejas
 *
 */

@RestController
@RequestMapping("/customer")
public class LoginLogoutCustomerController {

	@Autowired
	private LoginLogoutCustomerService loginLogoutCustomerService;

	@PostMapping("/login")
	public ResponseEntity<CurrentCustomerSession> loginCustomerHandler(@Valid @RequestBody User user)
			throws LoginException, CustomerException {

		CurrentCustomerSession currentCustomerSession = loginLogoutCustomerService.loginCustomer(user);

		return new ResponseEntity<CurrentCustomerSession>(currentCustomerSession, HttpStatus.OK);
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logoutCustomerHandler(@RequestParam String key)
			throws LogoutException, CurrentCustomerSessionException {

		String result = loginLogoutCustomerService.logoutCustomer(key);

		return new ResponseEntity<String>(result, HttpStatus.OK);

	}

	@PostMapping("/authenticate")
	public ResponseEntity<User> authenticateCustomerHandler(@Valid @RequestBody User user, @RequestParam String key)
			throws UserException, LoginException, CustomerException {

		User validated_user = loginLogoutCustomerService.authenticateCustomer(user, key);

		return new ResponseEntity<User>(validated_user, HttpStatus.OK);
	}

	@GetMapping("/validate")
	public ResponseEntity<Customer> validateCustomerHandler(@RequestParam String key)
			throws LoginException, CustomerException {

		Customer validated_customer = loginLogoutCustomerService.validateCustomer(key);

		return new ResponseEntity<Customer>(validated_customer, HttpStatus.OK);
	}

}
