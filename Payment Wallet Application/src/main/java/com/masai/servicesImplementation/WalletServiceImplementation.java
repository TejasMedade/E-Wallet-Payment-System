/**
 * 
 */
package com.masai.servicesImplementation;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.hibernate.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.BankAccountException;
import com.masai.exceptions.BeneficiaryException;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.UserException;
import com.masai.exceptions.WalletException;
import com.masai.model.BankAccount;
import com.masai.model.Beneficiary;
import com.masai.model.Customer;
import com.masai.model.Transaction;
import com.masai.model.User;
import com.masai.model.Wallet;
import com.masai.repository.BankAccountRepo;
import com.masai.repository.CustomerRepo;
import com.masai.repository.WalletRepo;
import com.masai.services.LoginLogoutCustomerService;
import com.masai.services.TransactionService;
import com.masai.services.WalletService;

/**
 * @author tejas
 *
 */
@Service
public class WalletServiceImplementation implements WalletService {

	@Autowired
	private LoginLogoutCustomerService loginLogoutCustomerService;

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private WalletRepo walletRepo;

	@Autowired
	private BankAccountRepo bankAccountRepo;

	@Override
	public Double showWalletBalance(String key) throws LoginException, CustomerException {

		Customer customer = loginLogoutCustomerService.validateCustomer(key);

		if (customer != null) {

			Wallet wallet = customer.getWallet();

			Double balance = wallet.getBalance();

			return balance;

		} else {
			throw new CustomerException("Invalid Customer Key, Please Login In !");
		}
	}

	@Override
	public Transaction transferFunds(User user, String key, String targetMobileNumber, Double amount,
			String description) throws LoginException, CustomerException, WalletException, UserException {

		User validate_user = loginLogoutCustomerService.authenticateCustomer(user, key);

		if (validate_user != null) {

			Optional<Customer> optionalcustomer = customerRepo.findById(user.getId());

			if (optionalcustomer != null) {

				Optional<Customer> optionaltargetcustomer = customerRepo.findById(targetMobileNumber);

				if (optionaltargetcustomer.isPresent()) {

					Customer customer = optionalcustomer.get();

					Customer targetcustomer = optionaltargetcustomer.get();

					Wallet wallet = customer.getWallet();

					Wallet targetwallet = targetcustomer.getWallet();

					Double availableBalance = wallet.getBalance();

					Double targetavailableBalance = targetwallet.getBalance();

					List<Transaction> targetlistoftransactions = targetwallet.getListofTransactions();

					if (availableBalance >= amount) {

						Transaction transaction = transactionService.addTransaction(key, targetMobileNumber,
								description, "E-Wallet Transaction", amount);

						if (transaction != null) {

							wallet.setBalance(availableBalance - amount);

							targetlistoftransactions.add(transaction);

							targetwallet.setBalance(targetavailableBalance + amount);

							targetwallet.setListofTransactions(targetlistoftransactions);

							walletRepo.save(wallet);

							walletRepo.save(targetwallet);

							return transaction;

						} else {

							throw new TransactionException("Oops ! Transcation Failed ! ");
						}

					} else {
						throw new WalletException(
								"Insufficient Funds ! Available Wallet Balance : " + wallet.getBalance());
					}

				} else {

					throw new CustomerException("Reciever's Mobile Number is not Registered with E-wallet !");

				}

			} else {
				throw new CustomerException("Invalid Customer Key, Please Login In !");
			}

		} else {
			throw new UserException("Invalid User Id or Password!");
		}

	}

	@Override
	public Transaction transferFundsToBeneficiary(User user, String key, String benficiarymobileNumber, Double amount,
			String description) throws LoginException, CustomerException, WalletException, UserException,
			BankAccountException, BeneficiaryException {

		User validate_user = loginLogoutCustomerService.authenticateCustomer(user, key);

		if (validate_user != null) {

			Optional<Customer> optionalcustomer = customerRepo.findById(user.getId());

			if (optionalcustomer.isPresent()) {

				Customer customer = optionalcustomer.get();

				Wallet wallet = customer.getWallet();

				List<Beneficiary> listofbeneficiaries = wallet.getListofBeneficiaries();

				Beneficiary targetbeneficiary = null;

				for (Beneficiary b : listofbeneficiaries) {

					if (Objects.equals(b.getMobileNumber(), benficiarymobileNumber)) {

						targetbeneficiary = b;
					}
				}

				if (targetbeneficiary != null) {

					Optional<BankAccount> optionalBankAccount = bankAccountRepo.findById(customer.getMobileNumber());

					if (optionalBankAccount.isPresent()) {

						BankAccount bankAccount = optionalBankAccount.get();

						Double availableBalance = bankAccount.getBalance();

						if (availableBalance >= amount) {

							Transaction transaction = transactionService.addTransaction(key,
									targetbeneficiary.getAccountNo(), description, "Beneficiary Transaction", amount);

							if (transaction != null) {

								bankAccount.setBalance(availableBalance - amount);

								bankAccountRepo.save(bankAccount);

								return transaction;
							} else {
								throw new TransactionException("Oops ! Transcation Failed ! ");
							}

						} else {
							throw new WalletException(
									"Insufficient Funds ! Available Bank Balance : " + availableBalance);
						}

					} else {
						throw new BankAccountException("No Registered Bank Account Found With This Mobile Number : "
								+ customer.getMobileNumber());
					}
				} else {
					throw new BeneficiaryException(
							"No Registered Beneficiary Found with this Mobile Number : " + benficiarymobileNumber);
				}
			} else {
				throw new CustomerException("Invalid Customer Key, Please Login In !");
			}

		} else {
			throw new UserException("Invalid User Id or Password!");
		}

	}

	@Override
	public Transaction addMoneyToWallet(User user, String key, Double amount)
			throws UserException, LoginException, CustomerException, WalletException, BankAccountException {

		User validate_user = loginLogoutCustomerService.authenticateCustomer(user, key);

		if (validate_user != null) {

			Optional<Customer> optionalcustomer = customerRepo.findById(user.getId());

			if (optionalcustomer != null) {

				Customer customer = optionalcustomer.get();

				Wallet wallet = customer.getWallet();

				Double walletAvailableBalance = wallet.getBalance();

				Optional<BankAccount> optionalBankAccount = bankAccountRepo.findById(customer.getMobileNumber());

				if (optionalBankAccount.isPresent()) {

					BankAccount bankAccount = optionalBankAccount.get();

					Double availableBalance = bankAccount.getBalance();

					if (availableBalance >= amount) {

						wallet.setBalance(walletAvailableBalance + amount);

						Transaction transaction = transactionService.addTransaction(key, customer.getMobileNumber(),
								"Wallet Top-Up", "E-Wallet Transaction", amount);

						if (transaction != null) {

							bankAccount.setBalance(availableBalance - amount);

							bankAccountRepo.save(bankAccount);

							walletRepo.save(wallet);

							return transaction;

						} else {

							throw new TransactionException("Oops ! Transcation Failed ! ");
						}

					} else {
						throw new WalletException(
								"Insufficient Funds ! Available Bank Account Balance : " + availableBalance);
					}

				} else {
					throw new BankAccountException(
							"No Registered Bank Account Found With This Mobile Number : " + customer.getMobileNumber());
				}

			} else {
				throw new CustomerException("Invalid Customer Key, Please Login In !");
			}

		} else {
			throw new UserException("Invalid User Id or Password!");
		}

	}

	@Override
	public Transaction addMoneyToBank(User user, String key, Double amount)
			throws UserException, LoginException, CustomerException, WalletException, BankAccountException {

		User validate_user = loginLogoutCustomerService.authenticateCustomer(user, key);

		if (validate_user != null) {

			Optional<Customer> optionalcustomer = customerRepo.findById(user.getId());

			if (optionalcustomer != null) {

				Customer customer = optionalcustomer.get();

				Wallet wallet = customer.getWallet();

				Double walletAvailableBalance = wallet.getBalance();

				Optional<BankAccount> optionalBankAccount = bankAccountRepo.findById(customer.getMobileNumber());

				if (optionalBankAccount.isPresent()) {

					BankAccount bankAccount = optionalBankAccount.get();

					Double bankAvailableBalance = bankAccount.getBalance();

					if (walletAvailableBalance >= amount) {

						wallet.setBalance(walletAvailableBalance - amount);

						Transaction transaction = transactionService.addTransaction(key, customer.getMobileNumber(),
								"Transfer from Wallet to Bank Account", "E-Wallet Transaction", amount);

						if (transaction != null) {

							bankAccount.setBalance(bankAvailableBalance + amount);

							bankAccountRepo.save(bankAccount);

							walletRepo.save(wallet);

							return transaction;

						} else {

							throw new TransactionException("Oops ! Transcation Failed ! ");
						}

					} else {
						throw new WalletException(
								"Insufficient Funds ! Available Wallet Balance : " + walletAvailableBalance);
					}

				} else {
					throw new BankAccountException(
							"No Registered Bank Account Found With This Mobile Number : " + customer.getMobileNumber());
				}

			} else {
				throw new CustomerException("Invalid Customer Key, Please Login In !");
			}

		} else {
			throw new UserException("Invalid User Id or Password!");
		}

	}

}
