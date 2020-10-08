package com.lifebank.factory.responseFactory;

import com.lifebank.component.validator.validationChain.IValidation;
import com.lifebank.factory.responseFactory.response.*;
import com.lifebank.process.TransactionsProcess;
import org.springframework.stereotype.Component;

@Component
public class TransactionsResponseFactory implements IResponseFactory<TransactionsProcess> {

    public IResponse createResponse(int responseCode){
        switch (responseCode){
            case IValidation.SUCCESS:
                return null;
            case IValidation.INVALID_JWT:
                return new InvalidToken();
            case IValidation.BLOCKED_ACCOUNT:
                return new BlockedAccount();
            case IValidation.INVALID_ACCOUNT:
                return new InvalidAccount();
            case IValidation.ACC_NOT_FROM_USER:
                return new AccountNotFromUser();
            case IValidation.INVALID_DATE:
                return new InvalidDateRange();
            default:
                return null;
        }
    }
}
