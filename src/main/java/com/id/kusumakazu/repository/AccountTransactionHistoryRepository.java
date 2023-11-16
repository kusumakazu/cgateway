package com.id.kusumakazu.repository;

import com.id.kusumakazu.domain.AccountTransactionHistory;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AccountTransactionHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AccountTransactionHistoryRepository extends JpaRepository<AccountTransactionHistory, Long> {
}
