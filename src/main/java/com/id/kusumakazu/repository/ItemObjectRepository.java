package com.id.kusumakazu.repository;

import com.id.kusumakazu.domain.ItemObject;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ItemObject entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemObjectRepository extends JpaRepository<ItemObject, Long> {
}
