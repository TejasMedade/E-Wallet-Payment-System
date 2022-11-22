/**
 * 
 */
package com.masai.servicesImplementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.CurrentCustomerSessionException;
import com.masai.exceptions.CustomerException;
import com.masai.model.CurrentCustomerSession;
import com.masai.model.Customer;
import com.masai.repository.CurrentCustomerSessionRepo;
import com.masai.repository.CustomerRepo;
import com.masai.services.CurrentCustomerSessionService;

/**
 * @author tejas
 *
 */

@Service
public class CurrentCustomerSessionServiceImplementation implements CurrentCustomerSessionService {

	@Autowired
	private CurrentCustomerSessionRepo currentCustomerSessionRepo;

	@Autowired
	private CustomerRepo customerRepo;

	@Override
	public CurrentCustomerSession getCurrentCustomerSession(String key) throws CurrentCustomerSessionException {

		Optional<CurrentCustomerSession> optional_CurrentCustomerSession = currentCustomerSessionRepo.findByKey(key);

		if (optional_CurrentCustomerSession.isPresent()) {

			CurrentCustomerSession currentCustomerSession = optional_CurrentCustomerSession.get();

			return currentCustomerSession;
		} else {
			throw new CurrentCustomerSessionException("Invalid key, Please Login In !");
		}

	}

	@Override
	public Customer getCustomerDetails(String key) throws CurrentCustomerSessionException, CustomerException {

		Optional<CurrentCustomerSession> optional_CurrentUserSession = currentCustomerSessionRepo.findByKey(key);

		if (optional_CurrentUserSession.isPresent()) {

			CurrentCustomerSession currentCustomerSession = optional_CurrentUserSession.get();

			String current_customerId = currentCustomerSession.getCustomerMobileNumber();

			Optional<Customer> current_Customer = customerRepo.findById(current_customerId);

			if (current_Customer.isPresent()) {

				Customer customer = current_Customer.get();

				return customer;

			} else {
				throw new CustomerException("No Registered Customer Found !");
			}

		} else {
			throw new CurrentCustomerSessionException("Invalid key, Please Login In !");
		}

	}

	@Override
	public String getCurrentCustomerId(String key) throws CurrentCustomerSessionException {

		Optional<CurrentCustomerSession> optional_CurrentCustomerSession = currentCustomerSessionRepo.findByKey(key);

		if (optional_CurrentCustomerSession.isPresent()) {

			CurrentCustomerSession currentCustomerSession = optional_CurrentCustomerSession.get();

			String current_customerId = currentCustomerSession.getCustomerMobileNumber();

			return current_customerId;

		} else {

			throw new CurrentCustomerSessionException("Invalid key, Please Login In !");
		}

	}

}
