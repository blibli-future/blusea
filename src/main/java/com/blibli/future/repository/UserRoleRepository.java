package com.blibli.future.repository;

import com.blibli.future.model.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by dhika on 15/10/2016.
 */
@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {
}
