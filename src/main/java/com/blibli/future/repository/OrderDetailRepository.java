package com.blibli.future.repository;

import com.blibli.future.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by dhika on 26/11/2016.
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
