package com.te.brs.bookServiceImpl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.te.brs.bookService.CustomerService;
import com.te.brs.customerRepository.CustomerRepository;
import com.te.brs.dto.CustomerDTO;
import com.te.brs.entity.Customer;
import com.te.brs.exception.DuplicateCustomerException;
import com.te.brs.responseEntity.ResponseStructure;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceIMPL implements CustomerService {
	
	private final CustomerRepository customerRepository;
	
	@Override
	public ResponseEntity<ResponseStructure<CustomerDTO>> saveCustomer(CustomerDTO customerDTO)
			throws DuplicateCustomerException {
		
		if(customerRepository.existsByCustomerEmail(customerDTO.getCustomerEmail())){
			throw new DuplicateCustomerException("Customer WIth Email: "+customerDTO.getCustomerEmail()+" is already Present");
		}
			Customer customer = new Customer();
			BeanUtils.copyProperties(customerDTO, customer);
			
			Customer save = customerRepository.save(customer);
			CustomerDTO customerDTO2 = new CustomerDTO();
			BeanUtils.copyProperties(save, customerDTO2);
			
			ResponseStructure<CustomerDTO> responseStructure = new ResponseStructure<CustomerDTO>().setData(customerDTO2).setError(false).setMessage("Customer Created Successfully with enail: "+customerDTO2.getCustomerEmail()).setStatusCode(HttpStatus.CREATED.value());
			
			return ResponseEntity.ok(responseStructure);
	}

	@Override
	public List<CustomerDTO> saveAllCustomers(List<CustomerDTO> customerDTOs) throws DuplicateCustomerException {
		// TODO Auto-generated method stub
		return null;
	}

}
