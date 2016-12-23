package com.blibli.future.repository;


import com.blibli.future.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by ARDI on 11/2/2016.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByCustomerId(long Id);
    Order findByCustomerEmail(String email);

    List<Order> findByCustomerEmailAndStatus(String email, int status);
    List<Order> findByCateringEmailAndStatus(String email, int status);
}
