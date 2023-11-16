package com.id.kusumakazu.repository;

import com.id.kusumakazu.domain.PlayerInventory;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PlayerInventory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlayerInventoryRepository extends JpaRepository<PlayerInventory, Long> {
}
