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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.AddressException;
import com.masai.exceptions.AdminException;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.model.Address;
import com.masai.services.AddressService;

/**
 * @author tejas
 *
 */

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@PostMapping("/addAddress")
	public ResponseEntity<String> addAddressHandler(@RequestParam String key, @Valid @RequestBody Address address)
			throws CustomerException, LoginException {

		String result = addressService.addAddress(key, address);

		return new ResponseEntity<String>(result, HttpStatus.ACCEPTED);
	}

	@PutMapping("/updateAddress")
	public ResponseEntity<String> updateAddressHandler(@RequestParam String key, @Valid @RequestBody Address address)
			throws CustomerException, LoginException {

		String result = addressService.updateAddress(key, address);

		return new ResponseEntity<String>(result, HttpStatus.ACCEPTED);

	}

	@DeleteMapping("/deleteAddress")
	public ResponseEntity<String> removeAddressHandler(@RequestParam String key, @Valid @RequestParam Integer addressId)
			throws CustomerException, LoginException, AddressException {

		String result = addressService.removeAddress(key, addressId);

		return new ResponseEntity<String>(result, HttpStatus.OK);

	}

	// Admin Role
	@GetMapping("/allAddresses")
	public ResponseEntity<List<Address>> viewAllAddressesHandler(@RequestParam String key)
			throws CustomerException, LoginException, AddressException, AdminException {

		List<Address> listofaddresses = addressService.viewAllAddress(key);

		return new ResponseEntity<List<Address>>(listofaddresses, HttpStatus.OK);

	}

	@GetMapping("/viewAddress")
	public ResponseEntity<Address> viewAddressHandler(@RequestParam String key)
			throws CustomerException, LoginException, AddressException {

		Address address = addressService.viewAddress(key);

		return new ResponseEntity<Address>(address, HttpStatus.OK);
	}

}
