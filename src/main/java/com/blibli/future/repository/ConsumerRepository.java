package com.blibli.future.repository;

import com.blibli.future.model.Consumer;
import com.blibli.future.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConsumerRepository extends CrudRepository<Consumer, Long> {

	List<User> findByNickName(String nickName);
}
