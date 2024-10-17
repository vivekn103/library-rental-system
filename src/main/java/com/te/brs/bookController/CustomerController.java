package com.te.brs.bookController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.te.brs.bookService.CustomerService;
import com.te.brs.dto.CustomerDTO;
import com.te.brs.exception.DuplicateCustomerException;
import com.te.brs.responseEntity.ResponseStructure;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
	
	private final CustomerService customerService;
	
	@PostMapping("/register")
	public ResponseEntity<ResponseStructure<CustomerDTO>> registerCustomer(@Valid @RequestBody CustomerDTO customerDTO) throws DuplicateCustomerException{
		return customerService.saveCustomer(customerDTO);
	}
}
