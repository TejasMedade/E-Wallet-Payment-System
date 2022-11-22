/**
 * 
 */
package com.masai.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.AdminException;
import com.masai.exceptions.BankAccountException;
import com.masai.exceptions.CurrentAdminSessionException;
import com.masai.exceptions.CurrentCustomerSessionException;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.UserException;
import com.masai.model.BankAccount;
import com.masai.model.User;
import com.masai.services.BankAccountService;

/**
 * @author tejas
 *
 */
@RestController
@RequestMapping("/bankaccount")
public class BankAccountController {

	@Autowired
	private BankAccountService bankAccountService;

	@PostMapping("/add")
	public ResponseEntity<String> addBankAccountHandler(@Valid @RequestBody User user, @RequestParam String key,
			@Valid @RequestParam String mobileNumber, @Valid @RequestParam String bankName,
			@Valid @RequestParam String ifscCode, @Valid @RequestParam Double balance,
			@Valid @RequestParam String accountNo)
			throws CurrentCustomerSessionException, UserException, LoginException, CustomerException {

		String result = bankAccountService.addAccount(user, key, mobileNumber, bankName, ifscCode, balance, accountNo);

		return new ResponseEntity<String>(result, HttpStatus.ACCEPTED);

	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> removeAccountHandler(@Valid @RequestBody User user, @RequestParam String key)
			throws UserException, LoginException, CustomerException, BankAccountException,
			CurrentCustomerSessionException {

		String result = bankAccountService.removeAccount(user, key);

		return new ResponseEntity<String>(result, HttpStatus.OK);

	}

	@PostMapping("/view")
	public ResponseEntity<BankAccount> viewAccountHandler(@Valid @RequestBody User user, @RequestParam String key)
			throws UserException, LoginException, CustomerException, BankAccountException,
			CurrentCustomerSessionException {

		BankAccount bankAccount = bankAccountService.viewAccount(user, key);

		return new ResponseEntity<>(bankAccount, HttpStatus.OK);
	}

	@PostMapping("/view/balance")
	public ResponseEntity<Double> viewBankBalanceHandler(@Valid @RequestBody User user, @RequestParam String key)
			throws UserException, LoginException, CustomerException, BankAccountException,
			CurrentCustomerSessionException {

		Double balance = bankAccountService.viewBankBalance(user, key);

		return new ResponseEntity<>(balance, HttpStatus.OK);

	}

// Admin
	@PostMapping("/viewallaccounts")
	public ResponseEntity<List<BankAccount>> viewAllAccountsHandler(@Valid @RequestBody User user,
			@RequestParam String key)
			throws UserException, AdminException, LoginException, BankAccountException, CurrentAdminSessionException {

		List<BankAccount> listofaccounts = bankAccountService.viewAllAccounts(user, key);

		return new ResponseEntity<>(listofaccounts, HttpStatus.OK);
	}

}
