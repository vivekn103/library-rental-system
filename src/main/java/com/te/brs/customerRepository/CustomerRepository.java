package com.te.brs.customerRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.brs.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	Customer findByCustomerEmail(String email);

	boolean existsByCustomerEmail(String email);

}
