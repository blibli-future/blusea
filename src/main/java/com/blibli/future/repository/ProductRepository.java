package com.blibli.future.repository;

/**
 * Created by ARDI on 10/6/2016.
 */
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.blibli.future.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{
    List<Product> findByName(String name);
}
