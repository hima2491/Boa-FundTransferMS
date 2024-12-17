package com.tekarch.FundTransferMS.Repository;

import com.tekarch.FundTransferMS.Model.FundTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
public interface FundTransferRepository extends JpaRepository<FundTransfer, Long> {
    List<FundTransfer> findBySenderAccountIdOrReceiverAccountId(Integer senderId, Integer receiverId);
    List<FundTransfer> findByAmountBetween(BigDecimal min, BigDecimal max);
    List<FundTransfer> findByStatus(String status);
    List<FundTransfer> findByInitiatedAtBetween(LocalDateTime from, LocalDateTime to);
    List<FundTransfer> findByScheduledDate(LocalDateTime date);
    List<FundTransfer> findBySenderAccountIdAndStatus(Integer senderAccountId, String status);
    List<FundTransfer> findBySenderAccountId(Integer senderAccountId);



}
