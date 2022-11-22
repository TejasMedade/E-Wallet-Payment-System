/**
 * 
 */
package com.masai.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tejas
 *
 */

@Data
@Entity
@NoArgsConstructor

public class Wallet {

	@Id
	// Mobile Number
	@NotNull(message = "{Wallet.id.invalid}")
	@NotBlank(message = "{Wallet.id.invalid}")
	@NotEmpty(message = "{Wallet.id.invalid}")
	private String walletId;

	@NotNull(message = "{Wallet.Balance.invalid}")
	private Double balance;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Beneficiary> listofBeneficiaries = new ArrayList<>();

	@ElementCollection
	private List<Bill> listofBills = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL)
	private List<Transaction> listofTransactions = new ArrayList<>();
	
	
	
}
