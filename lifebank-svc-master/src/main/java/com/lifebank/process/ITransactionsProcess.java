package com.lifebank.process;

import com.lifebank.pojo.request.transactions.TransactionsRequest;
import org.springframework.http.ResponseEntity;

public interface ITransactionsProcess {
    ResponseEntity<?> process(TransactionsRequest req);
}
