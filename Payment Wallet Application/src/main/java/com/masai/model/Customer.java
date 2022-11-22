/**
 * 
 */
package com.masai.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tejas
 *
 */

@Entity
@Data
@NoArgsConstructor
public class Customer {

	@Id
	// Mobile Number
	@NotNull(message = "{Customer.contact.invalid}")
	@NotBlank(message = "{Customer.contact.invalid}")
	@NotEmpty(message = "{Customer.contact.invalid}")
	@Size(min = 10, max = 12,message = "{Customer.contact.invalid}")
	private String mobileNumber;

	@NotNull(message = "{Customer.name.invalid}")
	@NotBlank(message = "{Customer.name.invalid}")
	@NotEmpty(message = "{Customer.name.invalid}")
	private String firstName;

	@NotNull(message = "{Customer.name.invalid}")
	@NotBlank(message = "{Customer.name.invalid}")
	@NotEmpty(message = "{Customer.name.invalid}")
	private String lastName;

	@NotNull(message = "{Customer.password.invalid}")
	@NotBlank(message = "{Customer.password.invalid}")
	@NotEmpty(message = "{Customer.password.invalid}")
	@Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){6,12}$", message = "{Customer.password.invalid}")
	private String password;

	@OneToOne(cascade = CascadeType.ALL)
	private Address address;

	@NotNull(message = "{Customer.email.invalid}")
	@NotBlank(message = "{Customer.email.invalid}")
	@NotEmpty(message = "{Customer.email.invalid}")
	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE, message = "{Customer.email.invalid}")
	private String email;

	@OneToOne(cascade = CascadeType.ALL)
	private Wallet wallet;

}
