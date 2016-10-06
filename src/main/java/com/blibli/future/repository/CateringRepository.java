package com.blibli.future.repository;

/**
 * Created by ARDI on 10/6/2016.
 */
import org.springframework.data.repository.CrudRepository;

import com.blibli.future.model.Catering;

public interface CateringRepository extends CrudRepository<Catering, Long> {
    public Catering findByUsername(String username);
}