package com.tekarch.FundTransferMS.DTO;

import lombok.Data;

@Data
public class AccountsDTO {

    private Long accountId;
    private String accountNumber;
    private String accountType;
    private Double balance;
}
