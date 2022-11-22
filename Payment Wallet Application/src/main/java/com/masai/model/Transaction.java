/**
 * 
 */
package com.masai.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer transactionId;

	// Mobile Number
	@NotNull(message = "{Wallet.id.invalid}")
	@NotBlank(message = "{Wallet.id.invalid}")
	@NotEmpty(message = "{Wallet.id.invalid}")
	@Size(min = 10, max = 12, message = "{Wallet.id.invalid}")
	private String walletId;

	@NotNull(message = "{Transaction.type.invalid}")
	@NotBlank(message = "{Transaction.type.invalid}")
	@NotEmpty(message = "{Transaction.type.invalid}")
	private String type;

	@NotNull(message = "{Transaction.date.invalid}")
	private LocalDate date;

	@NotNull(message = "{Transaction.time.invalid}")
	private LocalTime time;

	@NotNull(message = "{Transaction.amount.invalid}")
	@Min(value = 0, message = "{Transaction.amount.invalid}")
	private Double amount;

	@NotNull(message = "{Transaction.description.invalid}")
	@NotBlank(message = "{Transaction.description.invalid}")
	@NotEmpty(message = "{Transaction.description.invalid}")
	private String description;

	@NotNull(message = "{Transaction.receiver.invalid}")
	@NotBlank(message = "{Transaction.receiver.invalid}")
	@NotEmpty(message = "{Transaction.receiver.invalid}")
	private String receiver;

}
