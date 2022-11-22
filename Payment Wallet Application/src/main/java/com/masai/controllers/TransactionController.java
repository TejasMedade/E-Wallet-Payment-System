/**
 * 
 */
package com.masai.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.AdminException;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.model.Transaction;
import com.masai.services.TransactionService;

/**
 * @author tejas
 *
 */
@RestController
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@PostMapping("/addtransaction")
	public ResponseEntity<Transaction> addTransactionHandler(@RequestParam String key,
			@Valid @RequestParam String receiver, @RequestParam String description,
			@Valid @RequestParam String transactionType, @Valid @RequestParam Double amount)
			throws CustomerException, LoginException {

		Transaction transaction = transactionService.addTransaction(key, receiver, description, transactionType,
				amount);

		return new ResponseEntity<>(transaction, HttpStatus.ACCEPTED);

	}

	@GetMapping("/view")
	public ResponseEntity<Transaction> viewTransactionHandler(@RequestParam String key,
			@Valid @RequestParam Integer transactionId) throws CustomerException, LoginException {

		Transaction transaction = transactionService.viewTransaction(key, transactionId);

		return new ResponseEntity<>(transaction, HttpStatus.OK);

	}

	@GetMapping("/viewall")
	public ResponseEntity<List<Transaction>> viewAllTransactionsHandler(@RequestParam String key)
			throws CustomerException, LoginException {

		List<Transaction> listoftransactions = transactionService.viewAllTransactions(key);

		return new ResponseEntity<>(listoftransactions, HttpStatus.OK);

	}

	// Admin
	@GetMapping("/view/customer")
	public ResponseEntity<List<Transaction>> viewAllTransactionsByCustomerHandler(@RequestParam String key,
			@Valid @RequestParam String mobileNumber) throws AdminException, LoginException, CustomerException {

		List<Transaction> listoftransactions = transactionService.viewAllTransactionsByCustomer(key, mobileNumber);

		return new ResponseEntity<>(listoftransactions, HttpStatus.OK);
	}

	// Admin
	@GetMapping("/view/customer/date")
	public ResponseEntity<List<Transaction>> viewAllTransactionsByCustomerByDateHandler(@RequestParam String key,
			@Valid @RequestParam String date, @Valid @RequestParam String mobileNumber)
			throws AdminException, LoginException, CustomerException {

		List<Transaction> listoftransactions = transactionService.viewAllTransactionsByCustomerByDate(key, date,
				mobileNumber);

		return new ResponseEntity<>(listoftransactions, HttpStatus.OK);

	}

	@GetMapping("/viewall/date")
	public ResponseEntity<List<Transaction>> viewTransactionByDateHandler(@RequestParam String key,
			@Valid @RequestParam String date) throws LoginException, CustomerException {

		List<Transaction> listoftransactions = transactionService.viewTransactionByDate(key, date);

		return new ResponseEntity<>(listoftransactions, HttpStatus.OK);
	}

}
