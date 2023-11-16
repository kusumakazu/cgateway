package com.id.kusumakazu.repository;

import com.id.kusumakazu.domain.PlayerClass;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PlayerClass entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlayerClassRepository extends JpaRepository<PlayerClass, Long> {
}
