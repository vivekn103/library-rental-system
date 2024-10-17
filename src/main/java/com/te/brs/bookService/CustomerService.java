package com.te.brs.bookService;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.te.brs.dto.CustomerDTO;
import com.te.brs.exception.DuplicateCustomerException;
import com.te.brs.responseEntity.ResponseStructure;

public interface CustomerService {
	ResponseEntity<ResponseStructure<CustomerDTO>> saveCustomer(CustomerDTO customerDTO) throws DuplicateCustomerException;
	List<CustomerDTO> saveAllCustomers(List<CustomerDTO> customerDTOs) throws DuplicateCustomerException;
}
