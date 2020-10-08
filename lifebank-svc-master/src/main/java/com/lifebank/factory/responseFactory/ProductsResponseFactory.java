package com.lifebank.factory.responseFactory;

import com.lifebank.component.parser.IProductsParser;
import com.lifebank.component.validator.validationChain.IValidation;
import com.lifebank.factory.responseFactory.response.BlockedAccount;
import com.lifebank.factory.responseFactory.response.IResponse;
import com.lifebank.factory.responseFactory.response.InvalidToken;
import com.lifebank.factory.responseFactory.response.ProductsSuccess;
import com.lifebank.process.ProductsProcess;
import com.lifebank.repository.IDaoProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductsResponseFactory implements IResponseFactory<ProductsProcess>{
    private IDaoProduct productRepository;
    private IProductsParser productsParser;

    @Autowired
    public ProductsResponseFactory(IDaoProduct productRepository, IProductsParser productsParser) {
        this.productRepository = productRepository;
        this.productsParser = productsParser;
    }

    public IResponse createResponse(int responseCode){
        switch (responseCode){
            case IValidation.SUCCESS:
                return new ProductsSuccess(productRepository, productsParser);
            case IValidation.INVALID_JWT:
                return new InvalidToken();
            case IValidation.BLOCKED_ACCOUNT:
                return new BlockedAccount();
            default:
                return null;
        }
    }
}
