package com.blibli.future.repository;

import com.blibli.future.model.Costumer;
import com.blibli.future.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CostumerRepository extends CrudRepository<Costumer, Long> {
	List<User> findByNickName(String nickName);
}
