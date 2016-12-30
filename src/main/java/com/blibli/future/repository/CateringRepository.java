package com.blibli.future.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.blibli.future.model.Catering;
import org.springframework.stereotype.Repository;

@Repository
public interface CateringRepository extends JpaRepository<Catering, Long> {
    Catering findByUsername(String username);
}
