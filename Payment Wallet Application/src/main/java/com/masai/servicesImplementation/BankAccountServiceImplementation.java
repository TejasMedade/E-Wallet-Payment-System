/**
 * 
 */
package com.masai.servicesImplementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.AdminException;
import com.masai.exceptions.BankAccountException;
import com.masai.exceptions.CurrentAdminSessionException;
import com.masai.exceptions.CurrentCustomerSessionException;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.UserException;
import com.masai.model.BankAccount;
import com.masai.model.CurrentAdminSession;
import com.masai.model.CurrentCustomerSession;
import com.masai.model.Customer;
import com.masai.model.User;
import com.masai.repository.BankAccountRepo;
import com.masai.repository.CurrentAdminSessionRepo;
import com.masai.repository.CurrentCustomerSessionRepo;
import com.masai.repository.CustomerRepo;
import com.masai.services.BankAccountService;

import java.util.List;
import java.util.Objects;

/**
 * @author tejas
 *
 */

@Service
public class BankAccountServiceImplementation implements BankAccountService {

	@Autowired
	private CurrentCustomerSessionRepo currentCustomerSessionRepo;

	@Autowired
	private CurrentAdminSessionRepo currentAdminSessionRepo;

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private BankAccountRepo bankAccountRepo;

	@Autowired
	private LoginLogoutCustomerServiceImplementation loginLogoutCustomerServiceImplementation;

	@Autowired
	private LoginLogoutAdminServiceImplementation loginLogoutAdminServiceImplementation;

	@Override
	public String addAccount(User user, String key, String mobileNumber, String bankName, String ifscCode,
			Double balance, String accountNo)
			throws CurrentCustomerSessionException, UserException, LoginException, CustomerException {

		User validated_user = loginLogoutCustomerServiceImplementation.authenticateCustomer(user, key);

		if (validated_user != null) {

			Optional<CurrentCustomerSession> optionalcurrentCustomerSession = currentCustomerSessionRepo.findByKey(key);

			if (optionalcurrentCustomerSession.isPresent()) {

				CurrentCustomerSession currentCustomerSession = optionalcurrentCustomerSession.get();

				Optional<Customer> optionalcustomer = customerRepo
						.findById(currentCustomerSession.getCustomerMobileNumber());

				if (optionalcustomer.isPresent()) {

					Customer customer = optionalcustomer.get();

					if (Objects.equals(customer.getMobileNumber(), mobileNumber)) {

						BankAccount bankAccount = new BankAccount();

						bankAccount.setWalletId(customer.getMobileNumber());
						bankAccount.setAccountNo(accountNo);
						bankAccount.setBalance(balance);
						bankAccount.setBankName(bankName);
						bankAccount.setIfscCode(ifscCode);

						bankAccountRepo.save(bankAccount);

						return "Bank Account Successfully Added !";

					} else {
						throw new CustomerException("Invalid Wallet ID, Please Enter Your Registered Mobile Number !");
					}

				} else {
					throw new CustomerException("No Registered Customer Found with this Mobile Number : "
							+ currentCustomerSession.getCustomerMobileNumber());
				}
			} else {
				throw new CurrentCustomerSessionException("Invalid Customer Key, Please Login In !");
			}

		} else {

			throw new UserException("Invalid User Id or Password !");
		}

	}

	@Override
	public String removeAccount(User user, String key) throws UserException, LoginException, CustomerException,
			BankAccountException, CurrentCustomerSessionException {

		User validated_user = loginLogoutCustomerServiceImplementation.authenticateCustomer(user, key);

		if (validated_user != null) {

			Optional<CurrentCustomerSession> optionalcurrentCustomerSession = currentCustomerSessionRepo.findByKey(key);

			if (optionalcurrentCustomerSession.isPresent()) {

				CurrentCustomerSession currentCustomerSession = optionalcurrentCustomerSession.get();

				Optional<BankAccount> optionalBankAccount = bankAccountRepo
						.findById(currentCustomerSession.getCustomerMobileNumber());

				if (optionalBankAccount.isPresent()) {

					bankAccountRepo.delete(optionalBankAccount.get());

					return "Bank Account Deleted Successfully !";

				} else {
					throw new BankAccountException("No Bank Account Found with this Mobile Number : "
							+ currentCustomerSession.getCustomerMobileNumber());
				}

			} else {
				throw new CurrentCustomerSessionException("Invalid Customer Key, Please Login In !");
			}

		} else {

			throw new UserException("Invalid User Id or Password !");
		}
	}

	@Override
	public BankAccount viewAccount(User user, String key) throws UserException, LoginException, CustomerException,
			BankAccountException, CurrentCustomerSessionException {

		User validated_user = loginLogoutCustomerServiceImplementation.authenticateCustomer(user, key);

		if (validated_user != null) {

			Optional<CurrentCustomerSession> optionalcurrentCustomerSession = currentCustomerSessionRepo.findByKey(key);

			if (optionalcurrentCustomerSession.isPresent()) {

				CurrentCustomerSession currentCustomerSession = optionalcurrentCustomerSession.get();

				Optional<BankAccount> optionalBankAccount = bankAccountRepo
						.findById(currentCustomerSession.getCustomerMobileNumber());

				if (optionalBankAccount.isPresent()) {

					BankAccount bankAccount = optionalBankAccount.get();

					return bankAccount;

				} else {
					throw new BankAccountException("No Bank Account Found with this Mobile Number : "
							+ currentCustomerSession.getCustomerMobileNumber());
				}

			} else {
				throw new CurrentCustomerSessionException("Invalid Customer Key, Please Login In !");
			}
		} else {
			throw new UserException("Invalid User Id or Password !");
		}
	}

	@Override
	public List<BankAccount> viewAllAccounts(User user, String key)
			throws UserException, AdminException, LoginException, BankAccountException, CurrentAdminSessionException {

		User validated_user = loginLogoutAdminServiceImplementation.authenticateAdmin(user, key);

		if (validated_user != null) {

			Optional<CurrentAdminSession> optionalcurrentAdminSession = currentAdminSessionRepo.findByKey(key);

			if (optionalcurrentAdminSession.isPresent()) {

				List<BankAccount> listofbankaccounts = bankAccountRepo.findAll();

				if (!listofbankaccounts.isEmpty()) {

					return listofbankaccounts;
				} else {
					throw new BankAccountException("No Bank Accounts Found in the DataBase !");
				}

			} else {
				throw new CurrentAdminSessionException("Invalid Admin Key, Please Login In !");
			}

		} else {
			throw new UserException("Invalid User Id or Password !");
		}

	}

	@Override
	public Double viewBankBalance(User user, String key) throws UserException, LoginException, CustomerException,
			BankAccountException, CurrentCustomerSessionException {

		User validated_user = loginLogoutCustomerServiceImplementation.authenticateCustomer(user, key);

		if (validated_user != null) {

			Optional<CurrentCustomerSession> optionalcurrentCustomerSession = currentCustomerSessionRepo.findByKey(key);

			if (optionalcurrentCustomerSession.isPresent()) {

				CurrentCustomerSession currentCustomerSession = optionalcurrentCustomerSession.get();

				Optional<BankAccount> optionalBankAccount = bankAccountRepo
						.findById(currentCustomerSession.getCustomerMobileNumber());

				if (optionalBankAccount.isPresent()) {

					BankAccount bankAccount = optionalBankAccount.get();

					return bankAccount.getBalance();

				} else {
					throw new BankAccountException("No Bank Account Found with this Mobile Number : "
							+ currentCustomerSession.getCustomerMobileNumber());
				}

			} else {
				throw new CurrentCustomerSessionException("Invalid Customer Key, Please Login In !");
			}
		} else {
			throw new UserException("Invalid User Id or Password !");
		}

	}

}
