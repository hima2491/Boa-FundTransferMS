package com.tekarch.FundTransferMS.Services;

import com.tekarch.FundTransferMS.DTO.AccountsDTO;
import com.tekarch.FundTransferMS.Repository.FundTransferRepository;
import com.tekarch.FundTransferMS.Services.Interface.FundTransferService;
import com.tekarch.FundTransferMS.Model.FundTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class FundTransferServiceImpl implements FundTransferService {

    @Autowired
    private FundTransferRepository fundTransferRepository;

    @Autowired
    private RestTemplate restTemplate;

   // String url = "http://localhost:8082/accounts";

   @Value("${accounts.ms.url}")
   String url;

//    @Value("${accounts.ms.url}")
//    String url;

    @Override
    public FundTransfer transferFunds(FundTransfer fundTransfer) {
        AccountsDTO receiverAccount = restTemplate.getForObject(url + "/" + fundTransfer.getReceiver_account_id(),
                AccountsDTO.class);

        AccountsDTO senderAccount = restTemplate.getForObject(url + "/" + fundTransfer.getSender_account_id(),
                AccountsDTO.class);

        if(receiverAccount.getAccountNumber() == null || senderAccount.getAccountNumber() == null) {
            throw new RuntimeException("Invalid Account");
        }


        receiverAccount.setBalance(receiverAccount.getBalance() + fundTransfer.getAmount());
        senderAccount.setBalance(senderAccount.getBalance() - fundTransfer.getAmount());
        restTemplate.put(url, senderAccount);
        restTemplate.put(url, receiverAccount);

        return fundTransferRepository.save(fundTransfer);
    }

    @Override
    public List<FundTransfer> getAllTransactions() {
        return fundTransferRepository.findAll();
    }

    @Override
    public Optional<FundTransfer> getTransactionById(Long transactionId) {
        return fundTransferRepository.findById(transactionId);
    }
}