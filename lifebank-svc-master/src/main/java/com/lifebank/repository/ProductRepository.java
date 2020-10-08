package com.lifebank.repository;

import com.lifebank.entity.PrdXPty;
import com.lifebank.entity.Product;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository  implements IDao<Product, Integer>, IDaoProduct{
    private IProductRepository productRepository;

    @Autowired
    public ProductRepository(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> findById(Integer id){
        return (Optional<Product>) productRepository.findById(id);
    }

    @Override
    public Iterable<Product> findAll(){
        return (Iterable<Product>) productRepository.findAll();
    }

    @Override
    public Iterable<PrdXPty> findAllProductsByUser(String user){
        return (Iterable<PrdXPty>) productRepository.findAllProductsByUser(user);
    }
}
