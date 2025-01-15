package com.tekarch.FundTransferMS.Services.Interface;

import com.tekarch.FundTransferMS.Model.FundTransfer;

import java.util.List;
import java.util.Optional;

public interface FundTransferService {
    FundTransfer transferFunds(FundTransfer fundTransfer);
    List<FundTransfer> getAllTransactions();
    Optional<FundTransfer> getTransactionById(Long transactionId);
}
