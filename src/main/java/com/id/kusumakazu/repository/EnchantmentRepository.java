package com.id.kusumakazu.repository;

import com.id.kusumakazu.domain.Enchantment;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Enchantment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnchantmentRepository extends JpaRepository<Enchantment, Long> {
}
