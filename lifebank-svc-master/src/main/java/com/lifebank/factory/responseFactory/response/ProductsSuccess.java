package com.lifebank.factory.responseFactory.response;

import com.lifebank.component.parser.IProductsParser;
import com.lifebank.entity.PrdXPty;
import com.lifebank.pojo.response.products.IProductsResponse;
import com.lifebank.repository.IDaoProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ProductsSuccess implements IResponse<String> {
    private IDaoProduct productRepository;
    private IProductsParser productsParser;
    private IProductsResponse response;
    private Iterable<PrdXPty> records;

    @Autowired
    public ProductsSuccess(IDaoProduct productRepository, IProductsParser productsParser) {
        this.productRepository = productRepository;
        this.productsParser = productsParser;
    }

    public ResponseEntity<?> responde(String input){
        //Obtenemos los productos que tiene el usuario
        records = productRepository.findAllProductsByUser(input);

        //Formateamos la data de acuerdo a la respuesta esperada por el contrato del servicio
        response = productsParser.parse(records);

        //Retornamos respuesta
        return new ResponseEntity<IProductsResponse>(response, HttpStatus.OK);
    }
}
