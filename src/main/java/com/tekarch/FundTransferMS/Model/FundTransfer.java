package com.tekarch.FundTransferMS.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "fund_transfers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FundTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transferId;

    private Integer senderAccountId;
    private Integer receiverAccountId;

    @Column(nullable = false)
    private BigDecimal amount;




    private String status;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime initiatedAt;

    private LocalDateTime completedAt;
    private LocalDateTime scheduledDate;

    private String frequency; // For recurring transfers
}
