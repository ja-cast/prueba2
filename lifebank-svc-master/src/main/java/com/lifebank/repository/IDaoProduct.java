package com.lifebank.repository;

import com.lifebank.entity.PrdXPty;

public interface IDaoProduct {
    Iterable<PrdXPty> findAllProductsByUser(String user);
}
