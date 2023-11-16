package com.id.kusumakazu.service;

import com.id.kusumakazu.service.dto.ArmorDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.id.kusumakazu.domain.Armor}.
 */
public interface ArmorService {

    /**
     * Save a armor.
     *
     * @param armorDTO the entity to save.
     * @return the persisted entity.
     */
    ArmorDTO save(ArmorDTO armorDTO);

    /**
     * Get all the armors.
     *
     * @return the list of entities.
     */
    List<ArmorDTO> findAll();


    /**
     * Get the "id" armor.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ArmorDTO> findOne(Long id);

    /**
     * Delete the "id" armor.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
