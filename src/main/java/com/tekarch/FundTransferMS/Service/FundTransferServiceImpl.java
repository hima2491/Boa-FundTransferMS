package com.tekarch.FundTransferMS.Service;
import com.tekarch.FundTransferMS.Model.FundTransfer;
import com.tekarch.FundTransferMS.Model.TransferStatus;
import com.tekarch.FundTransferMS.Repository.FundTransferRepository;
import com.tekarch.FundTransferMS.Service.Interface.FundTransferService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class FundTransferServiceImpl implements FundTransferService {

    @Autowired
    private FundTransferRepository repository;

    @Override
    public FundTransfer setupScheduledTransfer(FundTransfer transfer, LocalDateTime scheduledDate) {
        // Set the scheduled transfer details
        transfer.setStatus(String.valueOf(TransferStatus.SCHEDULED));  // Set the status as SCHEDULED
        transfer.setScheduledDate(scheduledDate);      // Set the scheduled date
        transfer.setInitiatedAt(LocalDateTime.now());  // Set the current time as initiatedAt

        // Save the transfer object in the database
        return repository.save(transfer);
    }

    @Override
    public FundTransfer initiateTransaction(FundTransfer transfer) {
        return null;
    }

    @Override
    public List<FundTransfer> getTransactions(Integer userId, String type, BigDecimal min, BigDecimal max) {
        // Business logic: Fetch transactions based on userId and optional filters
        List<FundTransfer> transactions = repository.findBySenderAccountIdOrReceiverAccountId(userId, userId);

        if (type != null) {
            transactions = transactions.stream()
                    .filter(t -> type.equalsIgnoreCase("credit") && t.getReceiverAccountId().equals(userId) ||
                            type.equalsIgnoreCase("debit") && t.getSenderAccountId().equals(userId))
                    .toList();
        }

        if (min != null && max != null) {
            transactions = transactions.stream()
                    .filter(t -> t.getAmount().compareTo(min) >= 0 && t.getAmount().compareTo(max) <= 0)
                    .toList();
        }

        return transactions;
    }


    @Override
    public List<FundTransfer> getTransactionsByAccount(Integer accountId) {
        return repository.findBySenderAccountIdOrReceiverAccountId(accountId, accountId);
    }

    @Override
    public FundTransfer getTransactionById(Long transactionId) {
        return repository.findById(transactionId).orElseThrow(() -> new RuntimeException("Not Found"));
    }

    @Override
    public void validateTransactionLimit(Integer accountId) {
        // Add your logic here to validate transaction limits
        // Example: Perform checks or business logic
        System.out.println("Validating transaction limits for account ID: " + accountId);
    }





    @Override
    public FundTransfer setupRecurringTransfer(FundTransfer transfer, LocalDateTime date, String frequency) {
        transfer.setScheduledDate(date);
        transfer.setFrequency(frequency);
        transfer.setStatus(String.valueOf(TransferStatus.valueOf("recurring")));
        return repository.save(transfer);
    }

    @Override
    public FundTransfer updateTransfer(Long paymentId, FundTransfer updatedTransfer) {
        FundTransfer existing = repository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Not Found"));
        existing.setAmount(updatedTransfer.getAmount());
        return repository.save(existing);
    }

    @Override
    public FundTransfer getScheduledTransferDetails(Long transferId) {
        return repository.findById(transferId).orElseThrow(() -> new RuntimeException("Not Found"));
    }

    @Override
    public FundTransfer getRecurringTransferDetails(Long paymentId) {
        return repository.findById(paymentId).orElseThrow(() -> new RuntimeException("Not Found"));
    }
    @Override
    public List<FundTransfer> getTransactionsByStatus(Integer userId, String status) {
        return repository.findBySenderAccountIdAndStatus(userId, status);
    }
    @Override
    public List<FundTransfer> getTransactionsByAccountId(Integer accountId) {
        return repository.findBySenderAccountId(accountId);
    }

    @Override
    public void createScheduledTransfer(FundTransfer transfer) {

    }


}
