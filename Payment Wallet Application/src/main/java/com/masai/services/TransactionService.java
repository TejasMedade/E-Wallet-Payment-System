/**
 * 
 */
package com.masai.services;

import java.util.List;

import com.masai.exceptions.AdminException;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.UserException;
import com.masai.exceptions.WalletException;
import com.masai.model.Beneficiary;
import com.masai.model.Transaction;
import com.masai.model.User;

/**
 * @author tejas
 *
 */
public interface TransactionService {

	public Transaction addTransaction(String key, String receiver, String description, String transactionType,
			Double amount) throws CustomerException, LoginException;

	public Transaction viewTransaction(String key, Integer transactionId) throws CustomerException, LoginException;

	public List<Transaction> viewAllTransactions(String key) throws CustomerException, LoginException;

	// Admin
	public List<Transaction> viewAllTransactionsByCustomer(String key, String mobileNumber)
			throws AdminException, LoginException, CustomerException;

	// Admin
	public List<Transaction> viewAllTransactionsByCustomerByDate(String key, String date, String mobileNumber)
			throws AdminException, LoginException, CustomerException;

	public List<Transaction> viewTransactionByDate(String key, String date) throws LoginException, CustomerException;

}
