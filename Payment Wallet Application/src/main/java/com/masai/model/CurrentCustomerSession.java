/**
 * 
 */
package com.masai.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentCustomerSession {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer currentSessionId;

	@NotNull(message = "{CurrentCustomer.id.invalid}")
	@NotBlank(message = "{CurrentCustomer.id.invalid}")
	@NotEmpty(message = "{CurrentCustomer.id.invalid}")
	@Size(min = 10, max = 12, message = "{CurrentCustomer.id.invalid}")
	private String customerMobileNumber;

	@NotNull(message = "{CurrentCustomer.key.invalid}")
	@NotBlank(message = "{CurrentCustomer.key.invalid}")
	@NotEmpty(message = "{CurrentCustomer.key.invalid}")
	@Size(min = 6, max = 6, message = "{CurrentCustomer.key.invalid}")
	private String key;

	@NotNull(message = "{CurrentCustomer.date.invalid}")
	private LocalDateTime localDateTime;

}
