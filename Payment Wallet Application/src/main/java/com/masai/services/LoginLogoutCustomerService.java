/**
 * 
 */
package com.masai.services;

import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.LogoutException;
import com.masai.exceptions.UserException;
import com.masai.model.CurrentCustomerSession;
import com.masai.model.Customer;
import com.masai.model.User;

/**
 * @author tejas
 *
 */

public interface LoginLogoutCustomerService {

	public CurrentCustomerSession loginCustomer(User user) throws LoginException, CustomerException;

	public String logoutCustomer(String key) throws LogoutException;
	
	public User authenticateCustomer(User user, String key) throws UserException, LoginException, CustomerException;

	public Customer validateCustomer(String key) throws LoginException, CustomerException;
}
