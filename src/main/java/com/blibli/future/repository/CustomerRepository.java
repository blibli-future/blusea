package com.blibli.future.repository;

import com.blibli.future.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Customer findByUsername(String username);
}
