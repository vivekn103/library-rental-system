package com.te.brs.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {
	private Integer customerId;
	@NotBlank(message = "Pleas Enter Name")
	private String customerName;
	@NotBlank(message = "Pleas Enter Email")
	@Email(message = "Pleas Enter Valid Email")
	private String customerEmail;

}
