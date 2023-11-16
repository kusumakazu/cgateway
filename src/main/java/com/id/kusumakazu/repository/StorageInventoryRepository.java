package com.id.kusumakazu.repository;

import com.id.kusumakazu.domain.StorageInventory;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the StorageInventory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StorageInventoryRepository extends JpaRepository<StorageInventory, Long> {
}
