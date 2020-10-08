package com.lifebank.repository;

import com.lifebank.entity.PrdXPty;
import com.lifebank.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IProductRepository extends CrudRepository<Product, Integer> {

    @Query("select new com.lifebank.entity.PrdXPty(t.description, p.id, p.name) " +
            "from Product p inner join ProductType t on p.productType = t.id " +
            "where p.user = :user")
    Iterable<PrdXPty> findAllProductsByUser(@Param("user") String user);
}
