/**
 * 
 */
package com.masai.servicesImplementation;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.AdminException;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.model.Admin;
import com.masai.model.Customer;
import com.masai.model.Wallet;
import com.masai.repository.CustomerRepo;
import com.masai.repository.WalletRepo;
import com.masai.services.CustomerService;

/**
 * @author tejas
 *
 */
@Service
public class CustomerServiceImplementation implements CustomerService {

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private LoginLogoutCustomerServiceImplementation loginLogoutCustomerServiceimplementation;

	@Autowired
	private LoginLogoutAdminServiceImplementation loginLogoutAdminServiceimplementation;

	@Override
	public Customer addCustomer(Customer customer) throws CustomerException {

		Optional<Customer> find_customer = customerRepo.findById(customer.getMobileNumber());

		if (find_customer.isEmpty()) {

			Wallet wallet = new Wallet();

			System.out.println("49");

			wallet.setBalance(0.0);

			wallet.setWalletId(customer.getMobileNumber());

			customer.setWallet(wallet);

			System.out.println(customer);
			System.out.println(wallet);

			Customer added_customer = customerRepo.save(customer);

			System.out.println("57");

			if (added_customer != null) {

				return added_customer;

			} else {
				throw new CustomerException("OOps, Sign Up Unsuccessfull !");
			}
		} else {
			throw new CustomerException(
					"Customer Already Registered With This Mobile Number : " + customer.getMobileNumber());
		}
	}

	@Override
	public Customer updateCustomer(String key, Customer customer) throws CustomerException, LoginException {

		Customer validate_customer = loginLogoutCustomerServiceimplementation.validateCustomer(key);

		if (validate_customer != null) {

			return customerRepo.save(customer);

		} else {
			throw new CustomerException("Invalid Key, Please Login In !");
		}

	}

	@Override
	public String removeCustomer(String key, String customerMobileNumber) throws CustomerException, LoginException {

		Customer validate_customer = loginLogoutCustomerServiceimplementation.validateCustomer(key);

		if (validate_customer != null) {

			customerRepo.deleteById(customerMobileNumber);

			return "Customer Deleted Successfully !";

		} else {
			throw new CustomerException("Invalid Key, Please Login In !");
		}

	}

	@Override
	public Customer viewCustomer(String key, String customerMobileNumber) throws CustomerException, LoginException {

		Customer validate_customer = loginLogoutCustomerServiceimplementation.validateCustomer(key);

		if (validate_customer != null) {

			Optional<Customer> optional_customer = customerRepo.findById(customerMobileNumber);

			if (optional_customer.isPresent()) {

				return optional_customer.get();
			} else {
				throw new CustomerException(
						"No Customer Found With The Customer Contact Number : " + customerMobileNumber);
			}

		} else {
			throw new CustomerException("Invalid Key, Please Login In !");
		}

	}

	@Override
	public List<Customer> viewAllCustomers(String key) throws AdminException, CustomerException, LoginException {

		Admin validate_admin = loginLogoutAdminServiceimplementation.validateAdmin(key);

		if (validate_admin != null) {

			List<Customer> listofcustomers = customerRepo.findAll();

			if (listofcustomers.isEmpty()) {
				throw new CustomerException("No Customers Available in the Database!");
			} else {
				return listofcustomers;
			}

		} else {
			throw new AdminException("Invalid Key, Please Login In as Admin!");
		}

	}

}
