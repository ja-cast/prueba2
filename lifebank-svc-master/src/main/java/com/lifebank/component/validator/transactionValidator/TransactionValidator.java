package com.lifebank.component.validator.transactionValidator;

import com.lifebank.component.JWT.IJWTManager;
import com.lifebank.component.validator.validationChain.AbstractValidation;
import com.lifebank.component.validator.validationChain.IValidation;
import com.lifebank.component.validator.validationChain.validations.AccountValidation;
import com.lifebank.component.validator.validationChain.validations.JWTValidation;
import com.lifebank.component.validator.validationChain.validations.TransactionsDateValidation;
import com.lifebank.component.validator.validationChain.validations.UserProfileValidation;
import com.lifebank.entity.Product;
import com.lifebank.entity.User;
import com.lifebank.pojo.request.transactions.TransactionsRequest;
import com.lifebank.pojo.request.validator.ValidationData;
import com.lifebank.repository.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionValidator implements ITransactionValidator {
    private IDao<Product, Integer> productRepository;
    private IJWTManager jwtManager;
    private IDao<User, String> userRepository;

    @Autowired
    public TransactionValidator(IDao<Product, Integer> productRepository, IJWTManager jwtManager, IDao<User, String> userRepository) {
        this.productRepository = productRepository;
        this.jwtManager = jwtManager;
        this.userRepository = userRepository;
    }

    public ValidationData validate(TransactionsRequest req) {
        try {
            //Iniciamos la información de validación
            ValidationData validationData = new ValidationData(IValidation.SUCCESS);
            validationData.setJwt(req.getJwt());
            validationData.setAccount(req.getAccount());
            validationData.setStartDate(req.getStartDate());
            validationData.setEndDate(req.getEndDate());

            //Instanciamos nuestros validadores
            List<AbstractValidation> validationHandlers = new ArrayList<>();
            validationHandlers.add(new JWTValidation(jwtManager));
            validationHandlers.add(new UserProfileValidation(userRepository));
            validationHandlers.add(new TransactionsDateValidation());
            validationHandlers.add(new AccountValidation(productRepository));

            //Creamos la cadena de comandos
            for (int i = 0; i < validationHandlers.size() - 1; i++) {
                validationHandlers.get(i).AddValidation(validationHandlers.get(i+1));
            }

            //Ejecutamos validaciones configuradas en la cadena
            return validationHandlers.get(0).validate(validationData);
        }catch (Exception e){
            return new ValidationData(IValidation.INVALID_JWT);
        }
    }
}
