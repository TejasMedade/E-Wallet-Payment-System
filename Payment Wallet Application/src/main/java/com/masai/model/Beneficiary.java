	/**
 * 
 */
package com.masai.model;

import javax.annotation.Generated;
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

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tejas
 *
 */

@Data
@Entity
@NoArgsConstructor
public class Beneficiary {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer beneficiaryId;

	// Mobile Number
	@NotNull(message = "{Beneficiary.Contact.invalid}")
	@NotBlank(message = "{Beneficiary.Contact.invalid}")
	@NotEmpty(message = "{Beneficiary.Contact.invalid}")
	@Size(min = 10, max = 12, message = "{Beneficiary.Contact.invalid}")
	private String mobileNumber;

	@NotNull(message = "{Beneficiary.Name.invalid}")
	@NotBlank(message = "{Beneficiary.Name.invalid}")
	@NotEmpty(message = "{Beneficiary.Name.invalid}")
	private String name;

	@NotNull(message = "{Beneficiary.Account.invalid}")
	@NotBlank(message = "{Beneficiary.Account.invalid}")
	@NotEmpty(message = "{Beneficiary.Account.invalid}")
	private String accountNo;

	@NotNull(message = "{Beneficiary.ifsc.invalid}")
	@NotBlank(message = "{Beneficiary.ifsc.invalid}")
	@NotEmpty(message = "{Beneficiary.ifsc.invalid}")
	private String ifscCode;

	@NotNull(message = "{Beneficiary.BankName.invalid}")
	@NotBlank(message = "{Beneficiary.BankName.invalid}")
	@NotEmpty(message = "{Beneficiary.BankName.invalid}")
	private String bankName;

}
