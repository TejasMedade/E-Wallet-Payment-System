/**
 * 
 */
package com.masai.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tejas
 *
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount {

	// Mobile Number
	@Id
	@NotNull(message = "{Wallet.id.invalid}")
	@NotBlank(message = "{Wallet.id.invalid}")
	@NotEmpty(message = "{Wallet.id.invalid}")
	@Size(min = 10, max = 12, message = "{Wallet.id.invalid}")
	private String walletId;

	@NotNull(message = "{Bank.Account.invalid}")
	@NotBlank(message = "{Bank.Account.invalid}")
	@NotEmpty(message = "{Bank.Account.invalid}")
	private String accountNo;

	@NotNull(message = "{Bank.ifsc.invalid}")
	@NotBlank(message = "{Bank.ifsc.invalid}")
	@NotEmpty(message = "{Bank.ifsc.invalid}")
	private String ifscCode;

	@NotNull(message = "{Bank.Name.invalid}")
	@NotBlank(message = "{Bank.Name.invalid}")
	@NotEmpty(message = "{Bank.Name.invalid}")
	private String bankName;

	@NotNull(message = "{Bank.Balance.invalid}")
	@Min(value = 0, message = "Minimum Balance Should be Zero !")
	private Double balance;
}
