package com.id.kusumakazu.repository;

import com.id.kusumakazu.domain.MasterAccount;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MasterAccount entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MasterAccountRepository extends JpaRepository<MasterAccount, Long> {
}
