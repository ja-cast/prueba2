package com.lifebank.controller;

import com.lifebank.pojo.request.transactions.TransactionsRequest;
import com.lifebank.process.ITransactionsProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Locale;

@Controller
public class TransactionsController {
    private ITransactionsProcess transactionsProcess;

    @Autowired
    public TransactionsController(ITransactionsProcess transactionsProcess) {
        this.transactionsProcess = transactionsProcess;
    }

    @GetMapping(value="transactions/{account}")
    public ResponseEntity<?> transactions(@RequestHeader("jwt") String jwt,
                                          @PathVariable Integer account,
                                          @RequestParam(name = "startDate") String startDate,
                                          @RequestParam(name = "endDate")String endDate){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
            return transactionsProcess.process(new TransactionsRequest(jwt, account, formatter.parse(startDate), formatter.parse(endDate)));
        }catch (Exception e){
            return new ResponseEntity<String>("Unhandled error", HttpStatus.NOT_FOUND);
        }
    }

}
