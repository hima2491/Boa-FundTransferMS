package com.tekarch.FundTransferMS.Repository;

import com.tekarch.FundTransferMS.Model.FundTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundTransferRepository extends JpaRepository<FundTransfer, Long> {
}
