package com.lifebank.repository;

import java.util.Optional;

public interface IDao<T, t> {

    Optional<T> findById(t id);
    Iterable<T> findAll();
}
