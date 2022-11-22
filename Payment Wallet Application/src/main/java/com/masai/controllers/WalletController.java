/**
 * 
 */
package com.masai.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.BankAccountException;
import com.masai.exceptions.BeneficiaryException;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.UserException;
import com.masai.exceptions.WalletException;
import com.masai.model.Beneficiary;
import com.masai.model.Transaction;
import com.masai.model.User;
import com.masai.services.WalletService;

/**
 * @author tejas
 *
 */
@RestController
@RequestMapping("/wallet")
public class WalletController {

	@Autowired
	private WalletService walletService;

	@GetMapping("/balance")
	public ResponseEntity<Double> showWalletBalanceHandler(@RequestParam String key)
			throws LoginException, CustomerException {

		Double balance = walletService.showWalletBalance(key);

		return new ResponseEntity<Double>(balance, HttpStatus.OK);

	}

	@PostMapping("/transfer")
	public ResponseEntity<Transaction> transferFundsHandler(@Valid @RequestBody User user, @RequestParam String key,
			@Valid @RequestParam String targetMobileNumber, @Valid @RequestParam Double amount,
			@Valid @RequestParam String description)
			throws LoginException, CustomerException, WalletException, UserException {

		Transaction transaction = walletService.transferFunds(user, key, targetMobileNumber, amount, description);

		return new ResponseEntity<Transaction>(transaction, HttpStatus.ACCEPTED);

	}

	@PostMapping("/transfertobeneficiary")
	public ResponseEntity<Transaction> transferFundsToBeneficiaryHandler(@Valid @RequestBody User user,
			@RequestParam String key, @Valid @RequestParam String beneficiaryMobileNumber,
			@Valid @RequestParam Double amount, @Valid @RequestParam String description) throws LoginException,
			CustomerException, WalletException, UserException, BankAccountException, BeneficiaryException {

		Transaction transaction = walletService.transferFundsToBeneficiary(user, key, beneficiaryMobileNumber, amount,
				description);

		return new ResponseEntity<Transaction>(transaction, HttpStatus.ACCEPTED);

	}

	@PostMapping("/transfer/towallet")
	public ResponseEntity<Transaction> addMoneyToWalletHandler(@Valid @RequestBody User user, @RequestParam String key,
			@Valid @RequestParam Double amount)
			throws UserException, LoginException, CustomerException, WalletException, BankAccountException {

		Transaction transaction = walletService.addMoneyToWallet(user, key, amount);

		return new ResponseEntity<Transaction>(transaction, HttpStatus.ACCEPTED);
	}

	@PostMapping("/transfer/tobank")
	public ResponseEntity<Transaction> addMoneyToBank(@Valid @RequestBody User user, @RequestParam String key,
			@Valid @RequestParam Double amount)
			throws UserException, LoginException, CustomerException, WalletException, BankAccountException {

		Transaction transaction = walletService.addMoneyToBank(user, key, amount);

		return new ResponseEntity<Transaction>(transaction, HttpStatus.ACCEPTED);
	}

}
