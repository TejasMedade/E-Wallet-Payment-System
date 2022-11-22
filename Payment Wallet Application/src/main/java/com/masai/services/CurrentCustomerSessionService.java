/**
 * 
 */
package com.masai.services;

import com.masai.exceptions.CurrentCustomerSessionException;
import com.masai.exceptions.CustomerException;
import com.masai.model.CurrentCustomerSession;
import com.masai.model.Customer;

/**
 * @author tejas
 *
 */

public interface CurrentCustomerSessionService {

	public CurrentCustomerSession getCurrentCustomerSession(String key) throws CurrentCustomerSessionException;

	public Customer getCustomerDetails(String key) throws CurrentCustomerSessionException, CustomerException;

	public String getCurrentCustomerId(String key) throws CurrentCustomerSessionException;

}
