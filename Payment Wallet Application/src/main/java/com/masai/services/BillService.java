/**
 * 
 */
package com.masai.services;

import java.util.List;

import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.WalletException;
import com.masai.model.Bill;
import com.masai.model.Transaction;

/**
 * @author tejas
 *
 */
public interface BillService {

	public Transaction BillPayment(String key, Bill bill) throws CustomerException, LoginException, WalletException;

	public List<Bill> viewBillPayments(String key) throws CustomerException, LoginException;

}
