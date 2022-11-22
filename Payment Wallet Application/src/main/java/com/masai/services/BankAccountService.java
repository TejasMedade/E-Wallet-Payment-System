/**
 * 
 */
package com.masai.services;

import java.util.List;

import com.masai.exceptions.AdminException;
import com.masai.exceptions.BankAccountException;
import com.masai.exceptions.CurrentAdminSessionException;
import com.masai.exceptions.CurrentCustomerSessionException;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.UserException;
import com.masai.model.BankAccount;
import com.masai.model.User;

/**
 * @author tejas
 *
 */
public interface BankAccountService {

	String addAccount(User user, String key, String mobileNumber, String bankName, String ifscCode, Double balance,
			String accountNo) throws CurrentCustomerSessionException, UserException, LoginException, CustomerException;

	public String removeAccount(User user, String key) throws UserException, LoginException, CustomerException,
			BankAccountException, CurrentCustomerSessionException;

	public BankAccount viewAccount(User user, String key) throws UserException, LoginException, CustomerException,
			BankAccountException, CurrentCustomerSessionException;

	public Double viewBankBalance(User user, String key) throws UserException, LoginException, CustomerException,
			BankAccountException, CurrentCustomerSessionException;

	// Admin
	public List<BankAccount> viewAllAccounts(User user, String key)
			throws UserException, AdminException, LoginException, BankAccountException, CurrentAdminSessionException;

}
