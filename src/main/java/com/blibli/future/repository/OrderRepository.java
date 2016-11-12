package com.blibli.future.repository;


import com.blibli.future.model.Order;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ARDI on 11/2/2016.
 */
public interface OrderRepository extends CrudRepository<Order, Long> {
    Order findByCustomerId(long Id);
    Order findByCustomerEmail(String email);
}
