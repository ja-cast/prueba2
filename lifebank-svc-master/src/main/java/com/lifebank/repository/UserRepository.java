package com.lifebank.repository;

import com.lifebank.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository implements IDao<User, String> {

    IUserRepository userRepository;

    @Autowired
    public UserRepository(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findById(String id) {
        return (Optional<User>) userRepository.findById(id);
    }

    @Override
    public Iterable<User> findAll() {
        return (Iterable<User>) userRepository.findAll();
    }
}
