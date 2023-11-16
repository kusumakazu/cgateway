package com.id.kusumakazu.repository;

import com.id.kusumakazu.domain.Armor;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Armor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ArmorRepository extends JpaRepository<Armor, Long> {
}
