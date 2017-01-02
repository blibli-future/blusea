package com.blibli.future.repository;

import com.blibli.future.model.PersistentLogin;
import org.springframework.data.annotation.Persistent;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ARDI on 12/31/2016.
 */
public interface PersistentLoginRepository extends JpaRepository<PersistentLogin , String> {

}
