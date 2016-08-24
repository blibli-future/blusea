package com.blibli.future.repository;

import com.blibli.future.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

	List<User> findByNickName(String nickName);
}
