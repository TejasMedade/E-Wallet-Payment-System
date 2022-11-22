package com.masai.model;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Bill {


	@NotNull(message = "{Bill.ConsumerNo.invalid}")
	@NotBlank(message = "{Bill.ConsumerNo.invalid}")
	@NotEmpty(message = "{Bill.ConsumerNo.invalid}")
	private String consumerNo;

	@NotNull(message = "{Bill.Type.invalid}")
	@NotBlank(message = "{Bill.Type.invalid}")
	@NotEmpty(message = "{Bill.Type.invalid}")
	private String billType;

	@NotNull(message = "{Bill.Amount.invalid}")
	@Min(value = 0, message = "{Bill.Amount.invalid}")
	private Double amount;

	@NotNull(message = "{Bill.Receiver.invalid}")
	@NotBlank(message = "{Bill.Receiver.invalid}")
	@NotEmpty(message = "{Bill.Receiver.invalid}")
	private String receiver;

	@NotNull(message = "{Bill.DateTime.invalid}")
	private LocalDateTime paymentDateTime;

}
