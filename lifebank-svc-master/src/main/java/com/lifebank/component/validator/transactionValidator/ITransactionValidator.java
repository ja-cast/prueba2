package com.lifebank.component.validator.transactionValidator;

import com.lifebank.pojo.request.transactions.TransactionsRequest;
import com.lifebank.pojo.request.validator.ValidationData;

public interface ITransactionValidator {
    ValidationData validate(TransactionsRequest req);
}
