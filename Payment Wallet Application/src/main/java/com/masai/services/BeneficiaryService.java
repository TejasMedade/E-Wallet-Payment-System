/**
 * 
 */
package com.masai.services;

import java.util.List;

import com.masai.exceptions.AdminException;
import com.masai.exceptions.BeneficiaryException;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.UserException;
import com.masai.model.Beneficiary;
import com.masai.model.User;

/**
 * @author tejas
 *
 */
public interface BeneficiaryService {

	public Beneficiary addBeneficiary(String key, Beneficiary beneficiary)
			throws CustomerException, LoginException, BeneficiaryException;

	public String deleteBeneficiary(String key, String beneficiaryMobileNumber)
			throws CustomerException, LoginException, BeneficiaryException;

	public List<Beneficiary> viewAllBeneficiaries(String key)
			throws BeneficiaryException, UserException, LoginException, CustomerException;

	// Admin
	public List<Beneficiary> viewAllBeneficiariesByCustomer(User user, String key, String customerMobileNumber)
			throws BeneficiaryException, UserException, LoginException, CustomerException, AdminException;

}
