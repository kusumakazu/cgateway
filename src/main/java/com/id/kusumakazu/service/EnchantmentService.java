package com.id.kusumakazu.service;

import com.id.kusumakazu.service.dto.EnchantmentDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.id.kusumakazu.domain.Enchantment}.
 */
public interface EnchantmentService {

    /**
     * Save a enchantment.
     *
     * @param enchantmentDTO the entity to save.
     * @return the persisted entity.
     */
    EnchantmentDTO save(EnchantmentDTO enchantmentDTO);

    /**
     * Get all the enchantments.
     *
     * @return the list of entities.
     */
    List<EnchantmentDTO> findAll();


    /**
     * Get the "id" enchantment.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EnchantmentDTO> findOne(Long id);

    /**
     * Delete the "id" enchantment.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
