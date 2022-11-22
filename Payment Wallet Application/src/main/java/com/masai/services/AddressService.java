/**
 * 
 */
package com.masai.services;

import java.util.List;

import com.masai.exceptions.AddressException;
import com.masai.exceptions.AdminException;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.model.Address;

/**
 * @author tejas
 *
 */

public interface AddressService {

	public String addAddress(String key, Address address) throws CustomerException, LoginException;

	public String updateAddress(String key, Address address) throws CustomerException, LoginException;

	public String removeAddress(String key, Integer addressId)
			throws CustomerException, LoginException, AddressException;

	// Admin
	public List<Address> viewAllAddress(String key) throws AdminException, LoginException, AddressException;

	public Address viewAddress(String key) throws CustomerException, LoginException, AddressException;

}
