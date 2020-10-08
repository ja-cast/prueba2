package com.lifebank.component.validator.validationChain.validations;

import com.lifebank.component.validator.validationChain.AbstractValidation;
import com.lifebank.component.validator.validationChain.IValidation;
import com.lifebank.entity.Product;
import com.lifebank.pojo.request.validator.ValidationData;
import com.lifebank.repository.IDao;

public class AccountValidation extends AbstractValidation {
    IDao<Product, Integer> productRepository;

    public AccountValidation(IDao<Product, Integer> productRepository) {
        this.productRepository = productRepository;
    }

    public ValidationData validate(ValidationData input){
        try{
            //Obtenemos la información del producto de la base de datos
            Product productEntity = (Product) productRepository.findById(input.getAccount()).get();

            if (!input.getUser().isEmpty()){
                if(!productEntity.getUser().equals(input.getUser()))
                    input.setResponseCode(ACC_NOT_FROM_USER);
            }

            return input.getResponseCode()== SUCCESS ? super.validate(input): input;
        }catch (Exception e){
            return new ValidationData(INVALID_ACCOUNT);

        }
    }
}
