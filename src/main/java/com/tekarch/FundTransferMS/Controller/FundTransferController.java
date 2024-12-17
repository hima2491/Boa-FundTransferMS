package com.tekarch.FundTransferMS.Controller;
import com.tekarch.FundTransferMS.Model.FundTransfer;
import com.tekarch.FundTransferMS.Model.TransferStatus;
import com.tekarch.FundTransferMS.Service.Interface.FundTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/fund-transfer")
public class FundTransferController {

    @Autowired
    private FundTransferService service;

    @PostMapping
    public ResponseEntity<FundTransfer> initiateTransaction(@RequestBody FundTransfer transfer) {
        return ResponseEntity.ok(service.initiateTransaction(transfer));
    }

    @GetMapping("/transaction")
    public ResponseEntity<List<FundTransfer>> getUserTransactions(
            @RequestParam("userID") Integer userId,
            @RequestParam(value = "Type", required = false) String type,
            @RequestParam(value = "min", required = false) BigDecimal minAmount,
            @RequestParam(value = "max", required = false) BigDecimal maxAmount) {
        // Call service to get transactions with filters
        List<FundTransfer> transactions = service.getTransactions(userId, type, minAmount, maxAmount);
        return ResponseEntity.ok(transactions);
    }


    @GetMapping("/transaction/{userid}")
    public ResponseEntity<List<FundTransfer>> getByDate(
            @PathVariable Integer userid,
            @RequestParam(required = false) LocalDateTime datefrom,
            @RequestParam(required = false) LocalDateTime dateto) {
        return ResponseEntity.ok(service.getTransactionsByAccount(userid));
    }

    @GetMapping("/transaction/account")
    public ResponseEntity<List<FundTransfer>> getByAccountId(
            @RequestParam("accountid") Integer accountId) {
        return ResponseEntity.ok(service.getTransactionsByAccountId(accountId));
    }

    @GetMapping("/transaction/status/{userid}")
    public ResponseEntity<List<FundTransfer>> getTransactionsByStatus(
            @PathVariable Integer userid,
            @RequestParam String status) {
        return ResponseEntity.ok(service.getTransactionsByStatus(userid, status));
    }





    @PostMapping("/schedule")
    public ResponseEntity<FundTransfer> scheduledTransfer(@RequestBody FundTransfer transfer,
                                                          @RequestParam String scheduled) {
        return ResponseEntity.ok(service.setupScheduledTransfer(transfer, LocalDateTime.parse(scheduled)));
    }


    @GetMapping(params = "recurring")
    public ResponseEntity<FundTransfer> recurringTransferDetails(@RequestParam Long paymentid) {
        return ResponseEntity.ok(service.getRecurringTransferDetails(paymentid));
    }

    @GetMapping("/limits/{id}/validate")
    public ResponseEntity<String> validateTransactionLimit(@PathVariable("id") Integer accountId) {
        // Call the service to validate transaction limit
        service.validateTransactionLimit(accountId);
        return ResponseEntity.ok("Transaction limit validation successful for account: " + accountId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelTransfer(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}
