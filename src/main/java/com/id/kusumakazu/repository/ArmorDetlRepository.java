package com.id.kusumakazu.repository;

import com.id.kusumakazu.domain.ArmorDetl;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ArmorDetl entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ArmorDetlRepository extends JpaRepository<ArmorDetl, Long> {
}
