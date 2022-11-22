
package com.masai.servicesImplementation;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.LogoutException;
import com.masai.exceptions.UserException;
import com.masai.model.CurrentCustomerSession;
import com.masai.model.Customer;
import com.masai.model.User;
import com.masai.repository.CurrentCustomerSessionRepo;
import com.masai.repository.CustomerRepo;
import com.masai.services.LoginLogoutCustomerService;

import net.bytebuddy.utility.RandomString;

/**
 * @author tejas
 *
 */

@Service
public class LoginLogoutCustomerServiceImplementation implements LoginLogoutCustomerService {

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private CurrentCustomerSessionRepo currentCustomerSessionRepo;

	@Override
	public CurrentCustomerSession loginCustomer(User user) throws LoginException, CustomerException {

		if ("Customer".equals(user.getRole())) {

			Optional<Customer> optional_customer = customerRepo.findById(user.getId());

			if (optional_customer.isPresent()) {

				Customer customer = optional_customer.get();

				Optional<CurrentCustomerSession> optional_CurrentUserSession = currentCustomerSessionRepo
						.findByCustomerMobileNumber(customer.getMobileNumber());

				if (optional_CurrentUserSession.isPresent()) {

					throw new LoginException(
							"User Already Logged In With This Customer Id : " + customer.getMobileNumber());
				} else {

					if (user.getId().equals(customer.getMobileNumber())
							&& user.getPassword().equals(customer.getPassword())) {

						CurrentCustomerSession currentCustomerSession = new CurrentCustomerSession();

						String key = RandomString.make(6);

						currentCustomerSession.setCustomerMobileNumber(customer.getMobileNumber());
						currentCustomerSession.setKey(key);
						currentCustomerSession.setLocalDateTime(LocalDateTime.now());

						return currentCustomerSessionRepo.save(currentCustomerSession);

					} else {
						throw new LoginException("Invalid User_Id or Password");
					}
				}

			} else {
				throw new CustomerException("No Registered Customer Found With This User_Id : " + user.getId());
			}

		} else {

			throw new LoginException("Please, Select Customer as Role to Login !");
		}

	}

	@Override
	public String logoutCustomer(String key) throws LogoutException {

		Optional<CurrentCustomerSession> currentCustomerSession = currentCustomerSessionRepo.findByKey(key);

		if (currentCustomerSession.isPresent()) {

			currentCustomerSessionRepo.delete(currentCustomerSession.get());

			return "Logged Out Successfully !";

		} else {
			throw new LogoutException("Invalid Key, No User Logged In !");
		}

	}

	// Use this methods for Payments or some Very Highly Secured Operations//
	@Override
	public User authenticateCustomer(User user, String key) throws UserException, LoginException, CustomerException {

		Optional<CurrentCustomerSession> optional_currentCustomerSession = currentCustomerSessionRepo.findByKey(key);

		if (optional_currentCustomerSession.isPresent()) {

			CurrentCustomerSession currentCustomerSession = optional_currentCustomerSession.get();

			Optional<Customer> optional_customer = customerRepo
					.findById(currentCustomerSession.getCustomerMobileNumber());

			if (optional_customer.isPresent()) {

				Customer customer = optional_customer.get();

				if (customer.getMobileNumber().equals(user.getId())
						&& customer.getPassword().equals(user.getPassword())) {

					return user;
				} else {
					throw new UserException("Invalid UserId or Password");
				}

			} else {
				throw new CustomerException("No Customer Found with this Customer Id : "
						+ currentCustomerSession.getCustomerMobileNumber());
			}

		} else {
			throw new LoginException("Invalid Key, Please Login In !");
		}
	}

	@Override
	public Customer validateCustomer(String key) throws CustomerException, LoginException {

		Optional<CurrentCustomerSession> optional_currentCustomerSession = currentCustomerSessionRepo.findByKey(key);

		if (optional_currentCustomerSession.isPresent()) {

			CurrentCustomerSession currentCustomerSession = optional_currentCustomerSession.get();

			Optional<Customer> optional_customer = customerRepo
					.findById(currentCustomerSession.getCustomerMobileNumber());

			if (optional_customer.isPresent()) {

				Customer customer = optional_customer.get();

				return customer;

			} else {
				throw new CustomerException("No Customer Found with this Customer Id : "
						+ currentCustomerSession.getCustomerMobileNumber());
			}

		} else {
			throw new LoginException("Invalid Key, Please Login In !");
		}
	}

}
