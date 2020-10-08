package com.lifebank.process;

import com.lifebank.component.validator.transactionValidator.ITransactionValidator;
import com.lifebank.factory.responseFactory.IResponseFactory;
import com.lifebank.factory.responseFactory.response.IResponse;
import com.lifebank.pojo.request.transactions.TransactionsRequest;
import com.lifebank.pojo.request.validator.ValidationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TransactionsProcess implements ITransactionsProcess {
    private ITransactionValidator transactionValidator;
    private IResponseFactory<TransactionsProcess> responseFactory;
    private ValidationData response;

    @Autowired
    public TransactionsProcess(ITransactionValidator transactionValidator, IResponseFactory<TransactionsProcess> responseFactory) {
        this.transactionValidator = transactionValidator;
        this.responseFactory = responseFactory;
    }

    public ResponseEntity<?> process(TransactionsRequest req){
        //Ejecutamos las validaciones del proceso
        response = transactionValidator.validate(req);

        //De acuerdo al código de respuesta obtenemos el objeto de respuesta adecuado
        IResponse objResponse = responseFactory.createResponse(response.getResponseCode());

        //Enviamos respuesta
        return objResponse.responde(response.getUser());
    }
}
