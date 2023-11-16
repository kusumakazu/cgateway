package com.id.kusumakazu.repository;

import com.id.kusumakazu.domain.WeaponDetl;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the WeaponDetl entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WeaponDetlRepository extends JpaRepository<WeaponDetl, Long> {
}
