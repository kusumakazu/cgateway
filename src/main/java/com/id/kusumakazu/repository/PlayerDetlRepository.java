package com.id.kusumakazu.repository;

import com.id.kusumakazu.domain.PlayerDetl;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PlayerDetl entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlayerDetlRepository extends JpaRepository<PlayerDetl, Long> {
}
