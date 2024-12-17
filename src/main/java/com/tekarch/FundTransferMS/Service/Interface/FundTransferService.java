package com.tekarch.FundTransferMS.Service.Interface;

import com.tekarch.FundTransferMS.Model.FundTransfer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface FundTransferService {
    FundTransfer initiateTransaction(FundTransfer transfer);
    List<FundTransfer> getTransactions(Integer userId, String type, BigDecimal min, BigDecimal max);
    List<FundTransfer> getTransactionsByAccount(Integer accountId);
    FundTransfer getTransactionById(Long transactionId);
    void validateTransactionLimit(Integer accountId);
    FundTransfer setupRecurringTransfer(FundTransfer transfer, LocalDateTime date, String frequency);
    FundTransfer updateTransfer(Long paymentId, FundTransfer updatedTransfer);
    FundTransfer getScheduledTransferDetails(Long transferId);
    FundTransfer getRecurringTransferDetails(Long paymentId);
    List<FundTransfer> getTransactionsByStatus(Integer userId, String status);
    List<FundTransfer> getTransactionsByAccountId(Integer accountId);
    void createScheduledTransfer(FundTransfer transfer);
    FundTransfer setupScheduledTransfer(FundTransfer transfer, LocalDateTime scheduledDate);



}
