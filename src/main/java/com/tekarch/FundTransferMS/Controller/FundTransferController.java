package com.tekarch.FundTransferMS.Controller;
import com.tekarch.FundTransferMS.Model.FundTransfer;
import com.tekarch.FundTransferMS.Services.FundTransferServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.http.HttpStatus;

import java.util.Optional;

@RestController
@RequestMapping ("/transactions")
public class FundTransferController {

    @Autowired
    private FundTransferServiceImpl transactionService;

    @PostMapping
    public ResponseEntity<FundTransfer> initiateTransfer(@RequestBody FundTransfer fundTransfer) {
        FundTransfer initiatedTransfer = transactionService.transferFunds(fundTransfer);
        return new ResponseEntity<>(initiatedTransfer, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FundTransfer>> getAllTransfers() {
        return new ResponseEntity<>(transactionService.getAllTransactions(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FundTransfer> getTransferById(@PathVariable Long id) {
        Optional<FundTransfer> transactions = transactionService.getTransactionById(id);
//        return transactions.isEmpty()
//                ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
//                : new ResponseEntity<>(transactions.get(), HttpStatus.OK);

        return transactions.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}