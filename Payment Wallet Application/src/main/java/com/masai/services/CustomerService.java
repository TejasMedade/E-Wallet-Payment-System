/**
 * 
 */
package com.masai.services;

import java.util.List;

import com.masai.exceptions.AdminException;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.model.Customer;

/**
 * @author tejas
 *
 */

public interface CustomerService {

	public Customer addCustomer(Customer customer) throws CustomerException;

	public Customer updateCustomer(String key, Customer customer) throws CustomerException, LoginException;

	public String removeCustomer(String key, String customer_Id) throws CustomerException, LoginException;

	public Customer viewCustomer(String key, String customer_Id) throws CustomerException, LoginException;

	// Check for Admin Role
	public List<Customer> viewAllCustomers(String key) throws AdminException, LoginException, CustomerException;

}
