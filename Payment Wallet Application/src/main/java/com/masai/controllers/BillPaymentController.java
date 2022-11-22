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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.WalletException;
import com.masai.model.Bill;
import com.masai.model.Transaction;
import com.masai.services.BillService;

/**
 * @author tejas
 *
 */
@RestController
@RequestMapping("/bill")
public class BillPaymentController {

	@Autowired
	private BillService billService;

	@PostMapping("/payment")
	public ResponseEntity<Transaction> BillPaymentHandler(@RequestParam String key, @Valid @RequestBody Bill bill)
			throws CustomerException, LoginException, WalletException {

		Transaction transaction = billService.BillPayment(key, bill);

		return new ResponseEntity<>(transaction, HttpStatus.ACCEPTED);

	}

	@GetMapping("/view/allpayments")
	public ResponseEntity<List<Bill>> ViewAllBillPaymentsHandler(@RequestParam String key)
			throws CustomerException, LoginException {

		List<Bill> listofbills = billService.viewBillPayments(key);

		return new ResponseEntity<>(listofbills, HttpStatus.OK);

	}

}
