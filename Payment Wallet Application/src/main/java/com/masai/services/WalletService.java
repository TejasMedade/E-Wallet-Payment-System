/**
 * 
 */
package com.masai.services;

import com.masai.exceptions.BankAccountException;
import com.masai.exceptions.BeneficiaryException;
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
public interface WalletService {

	public Double showWalletBalance(String key) throws LoginException, CustomerException;

	public Transaction transferFunds(User user, String key, String targetMobileNumber, Double amount,
			String description) throws LoginException, CustomerException, WalletException, UserException;

	public Transaction addMoneyToWallet(User user, String key, Double amount)
			throws UserException, LoginException, CustomerException, WalletException, BankAccountException;

	public Transaction addMoneyToBank(User user, String key, Double amount) throws UserException, LoginException, CustomerException, WalletException, BankAccountException;

	public Transaction transferFundsToBeneficiary(User user, String key, String benficiarymobileNumber, Double amount,
			String description) throws LoginException, CustomerException, WalletException, UserException, BankAccountException, BeneficiaryException;
}
