package com.user.services.repository;

import org.springframework.data.repository.CrudRepository;
import com.user.services.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
