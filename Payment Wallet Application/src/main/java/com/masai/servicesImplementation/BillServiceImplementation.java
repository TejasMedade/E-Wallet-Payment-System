/**
 * 
 */
package com.masai.servicesImplementation;

import java.util.List;

import org.hibernate.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.WalletException;
import com.masai.model.Bill;
import com.masai.model.Customer;
import com.masai.model.Transaction;
import com.masai.model.Wallet;
import com.masai.repository.TransactionRepo;
import com.masai.repository.WalletRepo;
import com.masai.services.BillService;
import com.masai.services.TransactionService;

/**
 * @author tejas
 *
 */
@Service
public class BillServiceImplementation implements BillService {

	@Autowired
	private LoginLogoutCustomerServiceImplementation loginLogoutCustomerServiceImplementation;

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private WalletRepo walletRepo;

	@Override
	public Transaction BillPayment(String key, Bill bill) throws CustomerException, LoginException, WalletException {

		Customer customer = loginLogoutCustomerServiceImplementation.validateCustomer(key);

		if (customer != null) {

			Wallet wallet = customer.getWallet();

			Double availablebalance = wallet.getBalance();

			List<Bill> listofbills = wallet.getListofBills();

			// make a Transaction and add transaction to transaction list

			if (availablebalance >= bill.getAmount()) {

				Transaction transaction = transactionService.addTransaction(key, bill.getReceiver(), bill.getBillType(),
						"Bill Payment", bill.getAmount());

				if (transaction != null) {

					listofbills.add(bill);

					wallet.setBalance(availablebalance - bill.getAmount());

					wallet.setListofBills(listofbills);

					walletRepo.save(wallet);

					return transaction;
				}

				else {
					throw new TransactionException("Opps ! Transaction Failed !");

				}
			} else {
				throw new WalletException("Insufficient Funds ! Available Wallet Balance : " + availablebalance);
			}

		} else {
			throw new CustomerException("Invalid Customer Key, Please Login In ! ");
		}

	}

	@Override
	public List<Bill> viewBillPayments(String key) throws CustomerException, LoginException {

		Customer customer = loginLogoutCustomerServiceImplementation.validateCustomer(key);

		if (customer != null) {

			Wallet wallet = customer.getWallet();

			return wallet.getListofBills();

		} else {
			throw new CustomerException("Invalid Customer Key, Please Login In ! ");
		}

	}

}
