package com.id.kusumakazu.repository;

import com.id.kusumakazu.domain.GameAccount;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the GameAccount entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GameAccountRepository extends JpaRepository<GameAccount, Long> {
}
