package com.blibli.future.repository;

import com.blibli.future.model.Customer;
import com.blibli.future.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
	List<Customer> findByUsername(String username);
}
