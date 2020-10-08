package com.lifebank.repository;

import org.springframework.data.repository.CrudRepository;
import com.lifebank.entity.User;

public interface IUserRepository extends CrudRepository<User, String>{

}
